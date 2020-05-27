package com.sozin147.homeaccounting.services;

import com.sozin147.homeaccounting.model.ClientFile;

public interface FileService {
  void update(ClientFile file);
  void delete(ClientFile file);
}
