package com.sozin147.homeaccounting.services.impl;

import com.sozin147.homeaccounting.DAO.FileRepository;
import com.sozin147.homeaccounting.model.ClientFile;
import com.sozin147.homeaccounting.services.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
  private final FileRepository fileRepository;

  public FileServiceImpl(FileRepository fileRepository) {
    this.fileRepository = fileRepository;
  }

  @Override
  public void update(ClientFile file) {
    fileRepository.save(file);
  }

  @Override
  public void delete(ClientFile file) {
    fileRepository.delete(file);
  }
}
