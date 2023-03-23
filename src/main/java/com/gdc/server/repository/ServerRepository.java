package com.gdc.server.repository;

import com.gdc.server.model.ServerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<ServerModel, Long> {
    ServerModel findByIpAddress(String ipAddress);
}
