package com.raulvitorl.cursomc.services.validaton;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.raulvitorl.cursomc.domain.Cliente;
import com.raulvitorl.cursomc.domain.enums.TipoCliente;
import com.raulvitorl.cursomc.dto.ClienteNewDTO;
import com.raulvitorl.cursomc.repositories.ClienteRepository;
import com.raulvitorl.cursomc.resources.exceptions.FieldMessage;
import com.raulvitorl.cursomc.services.validaton.utils.BR;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista

		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){

			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));

		}

		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){

			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));

		}

		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());

		if(aux!=null)
			list.add(new FieldMessage("email", "Email já existente!"));

		aux = clienteRepository.findByCpfOuCnpj(objDto.getCpfOuCnpj());

		if(aux!=null && aux.getTipo() == TipoCliente.PESSOAFISICA)
			list.add(new FieldMessage("cpfOuCnpj", "CPF ja está em uso!"));

		if(aux!=null && aux.getTipo() == TipoCliente.PESSOAJURIDICA)
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ ja está em uso!"));


		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMesage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}