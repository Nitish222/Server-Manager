package com.nitish.server.service.implementation;

import com.nitish.server.enumeration.Status;
import com.nitish.server.model.Server;
import com.nitish.server.repo.ServerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.logging.Logger;

import static com.nitish.server.enumeration.Status.SERVER_DOWN;
import static com.nitish.server.enumeration.Status.SERVER_UP;

@RequiredArgsConstructor
@Service
@Transactional
public class ServerService implements com.nitish.server.service.ServerService {
    private final ServerRepo serverRepo;
    @Override
    public Server create(Server server) {
//        log.info("Saving new server: {}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000)? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
        return null;
    }

    @Override
    public Collection<Server> list(int limit) {
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    private String setServerImageUrl() {
        return  null;
    }

}
