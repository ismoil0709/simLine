package uz.pdp.simline.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.simline.entity.Balance;
import uz.pdp.simline.entity.SimCard;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Generator{
    public List<SimCard> generator(){
        List<SimCard> simCards = new ArrayList<>();
        for (int i = 0; i < 9999999; i++) {
            String s = String.format("%07d",i);
            simCards.add(new SimCard(
                    null,"+99877" + s,
                    new Balance(0D,0D,0L,0L)
                    ,15000D,
                    false,
                    null
            ));
        }
        return simCards;
    }
}