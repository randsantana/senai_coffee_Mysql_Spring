package br.com.senai.repositories;

import br.com.senai.models.Barber;
import br.com.senai.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository  extends JpaRepository<Barber,Long> {
}
