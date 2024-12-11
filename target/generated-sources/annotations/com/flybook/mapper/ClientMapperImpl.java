package com.flybook.mapper;

import com.flybook.model.dto.ClientDTO;
import com.flybook.model.entity.Client;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-11T11:45:33+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDTO clientEntityToClientDTOResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId( client.getId() );
        clientDTO.setFirstname( client.getFirstname() );
        clientDTO.setLastname( client.getLastname() );
        clientDTO.setEmail( client.getEmail() );
        clientDTO.setPassword( client.getPassword() );
        clientDTO.setRoles( client.getRoles() );

        return clientDTO;
    }

    @Override
    public Client clientDTOToClientEntity(ClientDTO clientDTO) {
        if ( clientDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( clientDTO.getId() );
        client.setFirstname( clientDTO.getFirstname() );
        client.setLastname( clientDTO.getLastname() );
        client.setEmail( clientDTO.getEmail() );
        client.setPassword( clientDTO.getPassword() );
        client.setRoles( clientDTO.getRoles() );

        return client;
    }
}
