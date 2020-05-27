package com.sozin147.homeaccounting.DAO;

import com.sozin147.homeaccounting.model.ClientFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<ClientFile, Long> {
}
