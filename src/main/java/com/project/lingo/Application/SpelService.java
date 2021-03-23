package com.project.lingo.Application;

import com.project.lingo.Data.repository.SpelRepository;
import com.project.lingo.Domain.Spel;
import com.project.lingo.Domain.Speler;
import com.project.lingo.Presentation.error.SpelNotFoundException;

import java.util.List;
import java.util.Optional;

public class SpelService implements ISpelService {
    private SpelRepository repository;

    public SpelService(SpelRepository spelRepository){
        this.repository = spelRepository;
    }

    @Override
    public List<Spel> vindAlle() {
        return (List<Spel>) repository.findAll();
    }

    @Override
    public Spel vindById(long id) {
        Optional<Spel> spel = repository.findById(id);
        if (!spel.isPresent())
            throw new SpelNotFoundException("Speler niet gevonden");
        return spel.get();
    }

    @Override
    public Spel start() {
        return null;
    }

    @Override
    public Spel nieuwSpel(Speler speler) {
        Spel spel = new Spel(speler);
        return repository.save(spel);
    }
}
