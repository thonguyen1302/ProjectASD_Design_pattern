package mum.asd.Service;

import mum.asd.domain.Room;
import mum.asd.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public void addProjectSample(int i){

        Room room = new Room();
        room.setBedType("Big Queen");
        room.setNumber(i);
        room.setNumberAdult(4);
        room.setNumberChildren(3);
        room.setPrice(200);
        room.setRoomType("Big Type");
        room.setTax(2);
        roomRepository.save(room);

    }


}
