package com.example.SocialMediaProject.connectionService.service.impl;

import com.example.SocialMediaProject.connectionService.auth.AuthContextHolder;
import com.example.SocialMediaProject.connectionService.entity.Person;
import com.example.SocialMediaProject.connectionService.repo.PersonRepo;
import com.example.SocialMediaProject.connectionService.service.ConnectionService;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionServiceImpl implements ConnectionService {

    private final PersonRepo personRepo;


    public List<Person> getDegreeConnectionOfUser(Long userId){

        log.info("getting first degree connection of userId:{}",userId);
        return personRepo.getFirstDegreeConnection(userId);

    }

    public void sendRequest(Long receiverId){
        Long senderId=AuthContextHolder.getCurrentUserId();
        log.info("sending connection request with senderId: {}, receiverId: {}", senderId, receiverId);

        if (senderId.equals(receiverId)) {
            throw new BadRequestException("Both sender and receiver are the same");
        }

        boolean alreadySentRequest = personRepo.connectionRequestExists(senderId, receiverId);
        if (alreadySentRequest) {
            throw new BadRequestException("Connection request already exists, cannot send again");
        }

        boolean alreadyConnected = personRepo.alreadyConnected(senderId, receiverId);
        if (alreadyConnected) {
            throw new BadRequestException("Already connected users, cannot add connection request");
        }

        personRepo.addConnectionRequest(senderId, receiverId);
        log.info("Successfully sent the connection request");

    }

    public void acceptConnectionRequest(Long senderId) {
        Long receiverId = AuthContextHolder.getCurrentUserId();
        log.info("Accepting a connection request with senderId: {}, receiverId: {}", senderId, receiverId);

        if (senderId.equals(receiverId)) {
            throw new BadRequestException("Both sender and receiver are the same");
        }

        boolean alreadyConnected = personRepo.alreadyConnected(senderId, receiverId);
        if (alreadyConnected) {
            throw new BadRequestException("Already connected users, cannot accept connection request again");
        }

        boolean alreadySentRequest = personRepo.connectionRequestExists(senderId, receiverId);
        if (!alreadySentRequest) {
            throw new BadRequestException("No Connection request exists, cannot accept without request");
        }

        personRepo.acceptConnectionRequest(senderId, receiverId);

        log.info("Successfully accepted the connection request with senderId: {}, receiverId: {}", senderId,
                receiverId);

    }

    public void rejectConnectionRequest(Long senderId) {
        Long receiverId = AuthContextHolder.getCurrentUserId();
        log.info("Rejecting a connection request with senderId: {}, receiverId: {}", senderId, receiverId);

        if (senderId.equals(receiverId)) {
            throw new BadRequestException("Both sender and receiver are the same");
        }

        boolean alreadySentRequest = personRepo.connectionRequestExists(senderId, receiverId);
        if (!alreadySentRequest) {
            throw new BadRequestException("No Connection request exists, cannot reject it");
        }

        personRepo.rejectConnectionRequest(senderId, receiverId);

        log.info("Successfully rejected the connection request with senderId: {}, receiverId: {}", senderId,
                receiverId);
    }


}
