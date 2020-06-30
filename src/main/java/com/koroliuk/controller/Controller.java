package com.koroliuk.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.koroliuk.entity.Attachment;
import com.koroliuk.entity.Comment;
import com.koroliuk.entity.Task;
import com.koroliuk.entity.User;
import com.koroliuk.entity.UserRating;
import com.koroliuk.model.Department;
import com.koroliuk.model.request.CommentRequest;
import com.koroliuk.model.request.TaskRequest;
import com.koroliuk.model.request.UserRequest;
import com.koroliuk.model.response.CommentResponse;
import com.koroliuk.model.response.TaskResponse;
import com.koroliuk.model.response.UserRatingResponse;
import com.koroliuk.model.response.UserResponse;
import com.koroliuk.repository.AttachmentRepository;
import com.koroliuk.repository.CommentRepository;
import com.koroliuk.repository.TaskRepository;
import com.koroliuk.repository.UserRatingRepository;
import com.koroliuk.repository.UserRepository;

@RestController
public class Controller {

  private static final Logger logger = LogManager.getLogger(Controller.class);

  private static String UPLOADED_FOLDER = "files/";
  
  private UserRepository userRepository;
  private TaskRepository taskRepository;
  private CommentRepository commentRepository;
  private AttachmentRepository attachmentRepository;
  private UserRatingRepository userRatingRepository;

  @Autowired
  public Controller(UserRepository userRepository, TaskRepository taskRepository, CommentRepository commentRepository,  
                    AttachmentRepository attachmentRepository, UserRatingRepository userRatingRepository) {
    this.userRepository = userRepository;
    this.taskRepository = taskRepository;
    this.commentRepository = commentRepository;
    this.attachmentRepository = attachmentRepository;
    this.userRatingRepository = userRatingRepository;
  }

  @PostMapping(value = "/tasks/task")
  public ResponseEntity<TaskResponse> task(@RequestBody TaskRequest task) {
    Task entity = new Task();
    entity.setSummary(task.getSummary());
    entity.setDescription(task.getDescription());
    entity.setPriority(task.getPriority());
    entity.setDepartment(task.getDepartment());
    if(!userRepository.existsById(task.getUserId())) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There no User by id " + task.getUserId());
    }
    User user = userRepository.findById(task.getUserId()).get();
    entity.setReporter(user);
    taskRepository.save(entity);
    user.getTasks().add(entity);
    userRepository.save(user);
    return ResponseEntity.ok(new TaskResponse(entity));
  }

  @GetMapping(value = "/tasks")
  public ResponseEntity<List<TaskResponse>> tasks(@RequestParam(name = "departmetn", required = false) Department department, 
                                                  @RequestParam(name = "sort_by_date_create", required = false) Boolean sortByDateCreate) {
    
    List<Task> listOfAllTasks;
    if (department != null) {
      listOfAllTasks = (List<Task>) taskRepository.findAllByDepartment(department);
    } else {
      listOfAllTasks = (List<Task>) taskRepository.findAll();
    }
    
    List<TaskResponse> response = new ArrayList<>();
    for (Task entity : listOfAllTasks) {
      response.add(new TaskResponse(entity));      
    }
    
    if (sortByDateCreate != null && sortByDateCreate) {
      Collections.sort(listOfAllTasks, new Comparator<Task>() {
        public int compare(Task o1, Task o2) {
          return o1.getDateCreate().compareTo(o2.getDateCreate());
        }
      });
    }    
    return ResponseEntity.ok(response);
  }
  
  @GetMapping(value = "/tasks/task/{task_id}")
  public ResponseEntity<TaskResponse> taskById(@PathVariable(value = "task_id") Long id) {
    if(!taskRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There no Task by id " + id);
    }
    return ResponseEntity.ok(new TaskResponse(taskRepository.findById(id).get()));
  }  

  @GetMapping(value = "/tasks/task/{task_id}/comments")
  public ResponseEntity<List<CommentResponse>> taskAllComments(@PathVariable(value = "task_id") Long id){
    List<CommentResponse> response = new ArrayList<>();
    for (Comment entity : taskRepository.findById(id).get().getComments()) {
      response.add(new CommentResponse(entity));      
    }
    return ResponseEntity.ok(response);
  }
    
  @PostMapping("/tasks/task/{task_id}/upload") 
  public String fileUpload(@PathVariable(value = "task_id") Long id, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    if (file.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return "redirect:uploadStatus";
    }
    try {
      byte[] bytes = file.getBytes();
      Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
      Files.write(path, bytes);

      redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
      
      Task task = taskRepository.findById(id).get();
      Attachment attachment = new Attachment();
      attachment.setFileName(file.getOriginalFilename());
      attachment.setPath(path.toString());
      attachmentRepository.save(attachment);
      task.getAttachments().add(attachment);
      taskRepository.save(task);
      System.out.println();
    } catch (IOException e) {
      logger.error("File upload error ", e);
    }
    if(!taskRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There no Task by id " + id);
    }    
    return "You successfully uploaded '" + file.getOriginalFilename() + "'";
  }

  @PostMapping(value = "/comments/comment")
  public ResponseEntity<CommentResponse> comment(@RequestBody CommentRequest comment) {
    Comment entity = new Comment();
    entity.setText(comment.getText());
    User user = userRepository.findById(comment.getUserId()).get();
    entity.setAuthor(user);
    Task task = taskRepository.findById(comment.getTaskId()).get();
    entity.setTask(task);
    commentRepository.save(entity);
    user.getComments().add(entity);
    userRepository.save(user);
    task.getComments().add(entity);
    taskRepository.save(task);
    return ResponseEntity.ok(new CommentResponse(entity));
  }

  @GetMapping(value = "/comments")
  public ResponseEntity<List<CommentResponse>> comments() {
    List<Comment> listOfAllComments = (List<Comment>) commentRepository.findAll();
    List<CommentResponse> response = new ArrayList<>();
    for (Comment entity : listOfAllComments) {
      response.add(new CommentResponse(entity));      
    }
    return ResponseEntity.ok(response);
  }
  
  @GetMapping(value = "/comments/comment/{comment_id}")
  public ResponseEntity<CommentResponse> commentById(@PathVariable(value = "comment_id") Long id) {
    if(!commentRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There no Comment by id " + id);
    }
    return ResponseEntity.ok(new CommentResponse(commentRepository.findById(id).get()));
  }    
  
  @DeleteMapping(value = "/comments/comment/{comment_id}")
  public ResponseEntity<String> taskDeleteComment(@PathVariable(value = "comment_id") Long id){
    if(!commentRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There no Comment by id " + id);
    }
    commentRepository.deleteById(id);
    return ResponseEntity.ok("Comment is delete!");
  }

  @PostMapping(value = "/users/user")
  public ResponseEntity<UserResponse> user(@RequestBody UserRequest user) {
    if (user.getName().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameter 'name' must be not empty!");
    }    
    User entity = new User();
    entity.setName(user.getName());
    entity.setDepartment(user.getDepartment());
    userRepository.save(entity);
    return ResponseEntity.ok(new UserResponse(entity));
  }

  @GetMapping(value = "/users")
  public ResponseEntity<List<UserResponse>> users() {
    List<User> listOfAllUsers = (List<User>) userRepository.findAll();
    List<UserResponse> response = new ArrayList<>();
    for (User entity : listOfAllUsers) {
      response.add(new UserResponse(entity));      
    }
    return ResponseEntity.ok(response);
  }
  
  @GetMapping(value = "/users/user/{user_id}")
  public ResponseEntity<UserResponse> userById(@PathVariable(value = "user_id") Long id) {
    if(!userRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There no User by id " + id);
    }
    return ResponseEntity.ok(new UserResponse(userRepository.findById(id).get()));
  }  
  
  @GetMapping(value = "/users/user/{user_id}/rating")
  public ResponseEntity<UserRatingResponse> userRating(@PathVariable(value = "user_id") Long id) {   
    UserRating userRating = null;
    List<UserRating> list = (List<UserRating>) userRatingRepository.findAll();
    for (UserRating uRating : list) {
      if(uRating.getUserId().equals(id)) {
        userRating = uRating;
      } 
    }
    return ResponseEntity.ok(new UserRatingResponse(userRating));
  } 

}
