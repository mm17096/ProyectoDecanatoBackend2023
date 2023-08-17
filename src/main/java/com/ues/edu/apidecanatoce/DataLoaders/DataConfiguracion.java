package com.ues.edu.apidecanatoce.DataLoaders;

import com.ues.edu.apidecanatoce.entities.ConfigSoliVe;
import com.ues.edu.apidecanatoce.repositorys.ConfigSoliVeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataConfiguracion implements CommandLineRunner {
    private final ConfigSoliVeRepository configSoliVeRepository;

    @Autowired
    public DataConfiguracion(ConfigSoliVeRepository configSoliVeRepository) {
        this.configSoliVeRepository = configSoliVeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        ConfigSoliVe defaultConfigSoliVe = new ConfigSoliVe(1, false);

        if (!configSoliVeRepository.existsById(1)){
            configSoliVeRepository.save(defaultConfigSoliVe);
        }
    }
}
