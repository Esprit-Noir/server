package com.gdc.server.service;

import com.gdc.server.model.ServerModel;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    ServerModel create(ServerModel server);
    ServerModel ping(String ipAddress) throws IOException;
    Collection<ServerModel> list(int limit);
    ServerModel get(Long id);
    ServerModel update(ServerModel server);
    Boolean delete(Long id);
}
