package com.nitish.server.repo;

import com.nitish.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public class ServerRepo extends JpaRepository<Server,Long> {
    Server findByIpAddress(String ipAddress);
    Server findByName(String name);


}
