package com.unirio;

import com.unirio.bean.*;
import java.util.*;

public class Principal {
	
	private static List<Departamento> departamentos = new ArrayList<Departamento>();
	private static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		int opcao = -1;

		while (opcao != 0) {
			System.out.println("\n--- MENU DO SISTEMA ---");
			System.out.println("1. Incluir, alterar, excluir e listar departamentos");
			System.out.println("2. Incluir funcionários em um departamento");
			System.out.println("3. Excluir funcionários de um departamento");
			System.out.println("4. Incluir contratados em um departamento");
			System.out.println("5. Excluir contratados de um departamento");
			System.out.println("6. Listar os funcionários e contratados vinculados a cada departamento");
			System.out.println("0. Sair");
			System.out.println("Escolha uma opção: ");

			try {
				opcao = Integer.parseInt(teclado.nextLine());

				switch (opcao) {
					case 1: gerenciarDepartamentos(); break;
					case 2: incluirFuncionario(); break;
					case 3: excluirFuncionario(); break;
					case 4: incluirContratado(); break;
					case 5: excluirContratado(); break;
					case 6: listarTudo(); break;
					case 0: System.out.println("Saindo..."); break;
					default: System.out.println("Opção inválida!");
				}
			} catch (Exception e) {
				System.out.println("Erro na entrada de dados. Tente novamente.");
			}
		}
	}

	private static int selecionarDepartamento() {
		if (departamentos.isEmpty()) {
			System.out.println("Nenhum departamento cadastrado.");
			return -1;
		}
		for (int i = 0; i < departamentos.size(); i++) {
			System.out.println("[" + i + "] " + departamentos.get(i).nomeDep);
		}
		System.out.println("Selecione o índice do Departamento: ");
		return Integer.parseInt(teclado.nextLine());
	}

	private static void gerenciarDepartamentos() {
		System.out.println("Escolha: [1] Incluir | [2] Alterar | [3] Excluir | [4] Listar: ");
		int op = Integer.parseInt(teclado.nextLine());

		if (op == 1) {
			System.out.println("Nome do Departamento: ");
			String nome = teclado.nextLine();
			departamentos.add(new Departamento(nome, new ArrayList<Contratado>(), new ArrayList<Funcionario>()));
			System.out.println("Incluído!");
		} else if (op == 2) {
			int idx = selecionarDepartamento();
			if (idx >= 0 && idx < departamentos.size()) {
				System.out.println("Novo nome: ");
				departamentos.get(idx).nomeDep = teclado.nextLine();
			}
		} else if (op == 3) {
			int idx = selecionarDepartamento();
			if (idx >= 0 && idx < departamentos.size()) {
				departamentos.remove(idx);
				System.out.println("Excluído!");
			}
		} else if (op == 4) {
			for (Departamento d : departamentos) System.out.println("- " + d.nomeDep);
		}
	}

	private static void incluirFuncionario() {
		int idx = selecionarDepartamento();
		if (idx >= 0 && idx < departamentos.size()) {
			Funcionario f = new Funcionario();
			System.out.print("Nome do Funcionário: ");
			f.setNome(teclado.nextLine());
			System.out.print("Número do Crachá: ");
			f.numCracha = Integer.parseInt(teclado.nextLine());
			
			departamentos.get(idx).funcionarios.add(f);
			System.out.println("Funcionário adicionado ao departamento!");
		}
	}

	private static void excluirFuncionario() {
		int idx = selecionarDepartamento();
		if (idx >= 0 && idx < departamentos.size()) {
			Departamento dep = departamentos.get(idx);
			for (int i = 0; i < dep.funcionarios.size(); i++) {
				System.out.println("[" + i + "] " + dep.funcionarios.get(i).getNome());
			}
			System.out.print("Selecione o funcionário para remover: ");
			int fIdx = Integer.parseInt(teclado.nextLine());
			if (fIdx >= 0 && fIdx < dep.funcionarios.size()) {
				dep.funcionarios.remove(fIdx);
				System.out.println("Removido!");
			}
		}
	}

	private static void incluirContratado() {
		int idx = selecionarDepartamento();
		if (idx >= 0 && idx < departamentos.size()) {
			Contratado c = new Contratado();
			System.out.print("Nome do Contratado: ");
			c.setNome(teclado.nextLine());
			System.out.print("Código do Contratado: ");
			c.codContratado = Integer.parseInt(teclado.nextLine());
			
			departamentos.get(idx).contratados.add(c);
			System.out.println("Contratado adicionado ao departamento!");
		}
	}

	private static void excluirContratado() {
		int idx = selecionarDepartamento();
		if (idx >= 0 && idx < departamentos.size()) {
			Departamento dep = departamentos.get(idx);
			for (int i = 0; i < dep.contratados.size(); i++) {
				System.out.println("[" + i + "] " + dep.contratados.get(i).getNome());
			}
			System.out.print("Selecione o contratado para remover: ");
			int cIdx = Integer.parseInt(teclado.nextLine());
			if (cIdx >= 0 && cIdx < dep.contratados.size()) {
				dep.contratados.remove(cIdx);
				System.out.println("Removido!");
			}
		}
	}

	private static void listarTudo() {
		if (departamentos.isEmpty()) System.out.println("Nenhum departamento cadastrado.");
		for (Departamento dep : departamentos) {
			System.out.println("\nDepartamento: " + dep.nomeDep);
			System.out.println("  • Funcionários:");
			for (Funcionario f : dep.funcionarios) System.out.println("    - " + f.getNome() + " (Crachá: " + f.numCracha + ")");
			System.out.println("  • Contratados:");
			for (Contratado c : dep.contratados) System.out.println("    - " + c.getNome() + " (Cód: " + c.codContratado + ")");
		}
	}
}