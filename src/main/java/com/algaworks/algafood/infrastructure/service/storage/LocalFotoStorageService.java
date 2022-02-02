package com.algaworks.algafood.infrastructure.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.algaworks.algafood.core.properties.StorageProperties;
import com.algaworks.algafood.domain.service.FotoStorageService;

public class LocalFotoStorageService implements FotoStorageService {
	
	@Autowired
	private StorageProperties storeProperties;

	@Override
	public void armazenar(NovaFoto novaFoto) {
		
		try {
			
			Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
		
			FileCopyUtils.copy(novaFoto.getInpuStream(), Files.newOutputStream(arquivoPath));
			
		} catch (IOException e) {
			throw new StorageException("Não foi possivel armazenar o arquivo", e);
		}
		
	}

	private Path getArquivoPath(String nomeArquivo) {
		Path dir = storeProperties.getLocal().getDiretorioFotos();
		return dir.resolve(Path.of(nomeArquivo));
	}

	@Override
	public void remover(String nomeArquivo) {
		try {
			
			Path arquivoPath = getArquivoPath(nomeArquivo);
		
			Files.deleteIfExists(arquivoPath);
		} catch (IOException e) {
			throw new StorageException("Não foi possivel excluir o arquivo", e);
		}
		
	}

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		try {
			
			Path arquivoPath = getArquivoPath(nomeArquivo);
			
			FotoRecuperada fotoRecuperada = FotoRecuperada.builder()
												.inputStream(Files.newInputStream(arquivoPath)).build();
			
			return fotoRecuperada;
			
		} catch (IOException e) {
			throw new StorageException("Não foi possivel recuperar o arquivo", e);
		}
		

	}

}
