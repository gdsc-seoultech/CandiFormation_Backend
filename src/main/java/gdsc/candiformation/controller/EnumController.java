package gdsc.candiformation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import gdsc.candiformation.constant.EnumMapper;
import gdsc.candiformation.constant.EnumValue;
import gdsc.candiformation.constant.UserRole;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EnumController {
    private final EnumMapper enumMapper;


    @GetMapping("/enum")
    public Map<String,Object> getEnum(){
        Map<String, Object> enums = new LinkedHashMap<>();
        Class userRole = UserRole.class;
        enums.put("userRole",userRole.getEnumConstants());
        return enums;
    }
    @GetMapping("/mapper")
    public Map<String, List<EnumValue>> getMapper() {
        return enumMapper.getAll();
    }
}
