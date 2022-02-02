package com.algaworks.algafood.infrastructure.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafood.core.properties.StorageProperties;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3FotoStorageService implements FotoStorageService {

	@Autowired
	private AmazonS3 amazonS3;
	@Autowired
	private StorageProperties storageProperties;
		
	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			
			String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
			
			var objectMetaData = new ObjectMetadata();
			objectMetaData.setContentType(novaFoto.getContentType());
			
			//payload
			var putObjectRequest = new PutObjectRequest(storageProperties.getS3().getBucket(), 
					caminhoArquivo, novaFoto.getInpuStream(), objectMetaData)
					.withCannedAcl(CannedAccessControlList.PublicRead);
			
			
			amazonS3.putObject(putObjectRequest);
			
		} catch (Exception e) {
			throw new StorageException("Não foi possível enviar o arquivo para AWS S3", e);
		}
	}


	@Override
	public void remover(String nomeArquivo) {
		
		try {
			String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
			
			//payload
			var deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), caminhoArquivo);
			
			amazonS3.deleteObject(deleteObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Não foi excluir o arquivo para AWS S3", e);
		}
		
	}

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		
		String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
		
		URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminhoArquivo);
		
		return FotoRecuperada.builder().url(url.toString()).build();
	}

	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFoto(), 
				nomeArquivo);
	}
}
