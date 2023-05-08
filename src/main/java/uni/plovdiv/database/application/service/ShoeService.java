package uni.plovdiv.database.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.database.application.repositories.ShoeRepository;

@Service
@RequiredArgsConstructor
public class ShoeService {

    private final ShoeRepository shoeRepository;
}
