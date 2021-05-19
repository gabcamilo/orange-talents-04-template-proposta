package br.com.zupacademy.gabriela.proposal.biometry;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.creditCard.CreditCardRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RequestMapping("/credit-cards/{creditCardId}/biometrics")
@RestController
public class BiometryWriteController {

    private final BiometryRepository biometryRepository;
    private final CreditCardRepository creditCardRepository;

    @Autowired
    public BiometryWriteController(BiometryRepository biometryRepository, CreditCardRepository creditCardRepository) {
        this.biometryRepository = biometryRepository;
        this.creditCardRepository = creditCardRepository;
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody CreateBiometryRequest request, @PathVariable Long creditCardId, UriComponentsBuilder uriBuilder) throws NotFoundException {

        Optional<CreditCard> creditCard = creditCardRepository.findById(creditCardId);

        if(creditCard.isEmpty()){
            throw new NotFoundException("/credit-cards/"+creditCardId+"/biometrics");
        }

        Biometry biometry = request.convert(creditCard.get());
        biometryRepository.save(biometry);
        URI uri = uriBuilder.path("credit-cards/{creditCardId}/biometrics/{id}").buildAndExpand(creditCardId, biometry.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
