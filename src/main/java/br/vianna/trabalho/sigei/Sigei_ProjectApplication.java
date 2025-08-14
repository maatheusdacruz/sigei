package br.vianna.trabalho.sigei;

import br.vianna.trabalho.sigei.model.Evento;
import br.vianna.trabalho.sigei.model.Participante;
import br.vianna.trabalho.sigei.model.Organizador;
import br.vianna.trabalho.sigei.model.repository.EventoDAO;
import br.vianna.trabalho.sigei.model.repository.ParticipanteDAO;
import br.vianna.trabalho.sigei.model.repository.OrganizadorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class Sigei_ProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Sigei_ProjectApplication.class, args);
	}

	@Autowired
	OrganizadorDAO fDao;

	@Autowired
	PasswordEncoder pass;

	@Autowired
	ParticipanteDAO cDao;

	@Autowired
	EventoDAO eDao;


	@Override
	public void run(String... args) throws Exception {
		System.out.println("### Server Started");

		Participante p = new Participante(0,"Zezin",
				"ze@ze","ze",pass.encode("123"), null, null);

		cDao.save(p);

		Organizador org = new Organizador(0,"Pedrin",
				"ped@ped","ped",pass.encode("123"));

		fDao.save(org);

		Organizador org1 = new Organizador(0,"Administrador da Silva",
				"admin@ped","admin",pass.encode("321"));

		fDao.save(org1);

		Participante p2 = new Participante(0, "Matheus", "matheus@matheus", "matheus", pass.encode("123"), null, null);

		cDao.save(p2);

		Organizador o2 = new Organizador(0, "pepaulo", "pepaulo@ricardo", "pepaulo", pass.encode("123"));

		fDao.save(o2);

		Evento e = new Evento(0, "BGS", "evento nerd de jogos e animes", "Tecnologia", LocalDate.of(2026, 1, 1), o2, null);

		eDao.save(e);

		Evento e2 = new Evento(0, "Evento de tecnologia", "evento de programacao", "Tecnologia", LocalDate.of(2026, 2, 1), o2, null);

		eDao.save(e2);
	}
}
