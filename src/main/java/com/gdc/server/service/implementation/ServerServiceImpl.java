package com.gdc.server.service.implementation;

import com.gdc.server.enumeration.Status;
import com.gdc.server.model.ServerModel;
import com.gdc.server.repository.ServerRepository;
import com.gdc.server.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {
    private final ServerRepository serverRepository;
    @Override
    public ServerModel create(ServerModel server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public ServerModel ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        ServerModel server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<ServerModel> list(int limit) {
        log.info("Fetching all servers");
        log.info(System.getenv("HOME"));
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public ServerModel get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public ServerModel update(ServerModel server) {
        log.info("Update server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Delete server by ID: {}", id);
        serverRepository.deleteById(id);
        return Boolean.TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames = {"server1.png","server2.png","server3.png","server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/images" + imageNames[new Random().nextInt(4)]).toUriString();
    }
}
