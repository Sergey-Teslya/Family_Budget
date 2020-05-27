package com.sozin147.homeaccounting.controllers;

import com.sozin147.homeaccounting.model.ClientFile;
import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.services.FileService;
import com.sozin147.homeaccounting.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FilesController {
  private final UserService userService;
  private final FileService fileService;

  public FilesController(UserService userService, FileService fileService) {
    this.userService = userService;
    this.fileService = fileService;
  }

  @PostMapping("/files")
  public ResponseEntity<byte[]> createFile(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal User activeUser) throws IOException {
    CustomUser user = userService.getUserByLogin(activeUser.getUsername());

    try {
      byte[] bytes = file.getBytes();
      fileService.update(new ClientFile(user, bytes));
    } catch (IOException e) {
      throw e;
    }

    return ResponseEntity
            .ok()
            .contentLength(file.getSize())
            .body(file.getBytes());
  }
}