package guru.sfg.common.events;

import com.beerservice.msscbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable{
    static final long serialVersionUID = 3819979646887464924L;
    private BeerDto beerDto;
}
