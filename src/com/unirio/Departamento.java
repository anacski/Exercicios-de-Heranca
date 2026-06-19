package com.unirio;

import com.unirio.bean.*;
import java.util.*;

public class Departamento extends ForcaDeTrabalho {
	
	public String nomeDep;
	
	public List<Contratado> contratados = new ArrayList<Contratado>();
	public List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	public Departamento(String nome, List<Contratado> contratados, List<Funcionario> funcionarios) {
		this.nomeDep = nome;
		this.contratados = contratados;
		this.funcionarios = funcionarios;
	}
}
