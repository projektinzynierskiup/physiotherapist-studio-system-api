package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.Mapper;
import up.krakow.pchysioterapist.api.dto.UsersDTO;
import up.krakow.pchysioterapist.api.model.Users;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDTO mapUsersToUsersDTO(Users users);
    Users mapUsersDTOtoUsers(UsersDTO usersDTO);
    List<Users> mapUserListToUsersDTOList(List<UsersDTO> usersDTOS);
    List<UsersDTO> mapUsersDTOListToUsersList(List<Users> usersList);
}
