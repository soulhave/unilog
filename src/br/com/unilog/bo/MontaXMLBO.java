package br.com.unilog.bo;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Logger;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import br.com.unilog.enuns.TipoNota;
import br.com.unilog.to.Carregamento;
import br.com.unilog.to.Nota;
import br.com.unilog.to.Vagao;

public class MontaXMLBO {
	
	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	private String XML_HEAD = 
		"<load:Header>"+
			"<load:OperationType>@operation@</load:OperationType>"+
			"<load:LoadingSeries>@serie@</load:LoadingSeries>"+
			"<load:LoadingNumber>@numero@</load:LoadingNumber>"+
			"<load:CreationDate>@dataCriacao@</load:CreationDate>"+
			"<load:FluxNumber>@fluxo@</load:FluxNumber>"+
			"<load:CalculationWeight>@peso@</load:CalculationWeight>"+
			"<load:RealWeight>@peso@</load:RealWeight>"+
			"<load:MerchandiseValue>@peso@</load:MerchandiseValue>"+
			"<load:Currency>BRL</load:Currency>"+
			"<load:UniqueLoading>@unico@</load:UniqueLoading>"+
			"<load:NumberPaidLinkedLoads>@qtdAtrelado@</load:NumberPaidLinkedLoads>"+
			"<load:NumberNonPaidLinkedLoads>0</load:NumberNonPaidLinkedLoads>"+
			"<load:RemunerationIndicator>R</load:RemunerationIndicator>"+
			"<load:WagonAmount>@qtdVagao@</load:WagonAmount>"+
			"<load:MerchandiseDetail>Detalhe</load:MerchandiseDetail>"+
		"</load:Header>";
	
	private String XML_NOTA_ELETRONICA = 
			"<load:Eletronic>"+
				"<load:Series>@serieNota@</load:Series>"+
				"<load:Number>@numeroNota@</load:Number>"+
				"<load:Date>@dataNota@</load:Date>"+
				"<load:Amount>@pesoNota@</load:Amount>"+
				"<load:TotalWeight>@pesoNota@</load:TotalWeight>"+
				"<load:Value>@valorNota@</load:Value>"+
				"<load:Key>@keyNota@</load:Key>"+
				"<load:PIN>500</load:PIN>"+
			"</load:Eletronic>";
	
	private String XML_NOTA_PAPEL = 
			"<load:paperInvoice>"+
				"<load:invoiceSerie>@serieNota@</load:invoiceSerie>"+
				"<load:invoiceNumber>@numeroNota@</load:invoiceNumber>"+
				"<load:invoiceCreationDate>@dataNota@</load:invoiceCreationDate>"+
				"<load:amount>@pesoNota@</load:amount>"+
				"<load:totalWeight>@pesoNota@</load:totalWeight>"+
				"<load:model>01</load:model>"+
				"<load:baseICMSValue>7.55</load:baseICMSValue>"+
				"<load:totalICMSValue>10.55</load:totalICMSValue>"+
				"<load:baseICMSSTValue>15.55</load:baseICMSSTValue>"+
				"<load:totalICMSSTValue>35.55</load:totalICMSSTValue>"+
				"<load:productTotalValue>@valorNota@</load:productTotalValue>"+
				"<load:invoiceTotalValue>@valorNota@</load:invoiceTotalValue>"+
				"<load:CFOP>1610</load:CFOP>"+
			"</load:paperInvoice>";
	
	private String XML_NOTA_OUTRO = 
			"<load:otherInvoice>"+
				"<load:invoiceSerie>@serieNota@</load:invoiceSerie>"+
				"<load:invoiceNumber>@numeroNota@</load:invoiceNumber>"+
				"<load:invoiceCreationDate>@dataNota@</load:invoiceCreationDate>"+
				"<load:amount>@pesoNota@</load:amount>"+
				"<load:totalWeight>@pesoNota@</load:totalWeight>"+
				"<load:totalValue>@valorNota@</load:totalValue>"+
				"<load:documentType>99</load:documentType>"+
			"</load:otherInvoice>";
	
	private String XML_CTE = 
			"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"+
			"<load:Request xmlns:load=\"http://www.vale.com/LG/20120001/02/LoadingUnicom\">"+
				"<load:Loading>"+
					"@header@"+
					"@vagoes@"+
					"<load:Invoice>"+
						"@notas@"+
					"</load:Invoice>"+
				"</load:Loading>"+
			"</load:Request>";
	
	private String XML_VAGAO = 
			"<load:Wagon>"+
				"<load:Number>@numeroVagao@</load:Number>"+
				"<load:Owner>@ownerWagao@</load:Owner>"+
				"<load:Type>@tipoVagao@</load:Type>"+
				"<load:CalculationWeight>@pesoNota@</load:CalculationWeight>"+
				"<load:RealWeight>@pesoNota@</load:RealWeight>"+
				"<load:TotalWeight>@pesoNota@</load:TotalWeight>"+
				"<load:Invoice>"+
					"<load:Series>@serieNota@</load:Series>"+
					"<load:Number>@numeroNota@</load:Number>"+
					"<load:Date>@dataNota@</load:Date>"+
					"<load:WagonWeight>@pesoNota@</load:WagonWeight>"+
				"</load:Invoice>"+
			"</load:Wagon>";
	
	public String criarXMLsend(Carregamento c) {
		
		//Header
		String header = XML_HEAD.replace("@operation@", c.getTipoOperacao().substring(0, 1))
				.replace("@serie@", c.getSerie())
				.replace("@numero@", c.getNumero())
				.replace("@dataCriacao@", c.getDataCriacao().toString())
				.replace("@fluxo@", c.getFluxo())
				.replace("@peso@", c.getPeso().toString())
				.replace("@unico@", (c.getUnico()?"S":"N"))
				.replace("@qtdAtrelado@", c.getAtrelados().toString())
				.replace("@qtdVagao@", Integer.toString(c.getVagoes().size()));
		
		//Vagão
		String vagoesRepace = "";
		
		for (Vagao v : c.getVagoes()) {
			
			String vagao = XML_VAGAO.replace("@numeroVagao@", v.getNumero())
					.replace("@ownerWagao@", v.getOwner())
					.replace("@tipoVagao@", v.getTipo())
					.replace("@pesoNota@", c.getPeso().toString())
					.replace("@serieNota@", v.getNota().getSerie())
					.replace("@numeroNota@", v.getNota().getNumero())
					.replace("@dataNota@", v.getNota().getDate().toString());
			
			vagoesRepace+=vagao;
			
		}
		
		//Notas
		String notasReplace = "";
		
		for (Nota n : c.getNotas()) {
			String nota = "";
			
			if(TipoNota.NFE.equals(n.getTipoNota())){
				nota=XML_NOTA_ELETRONICA;
				nota=nota.replace("@keyNota@", n.getNfeKey());
			}
			if(TipoNota.NFP.equals(n.getTipoNota())){
				nota=XML_NOTA_OUTRO;
			}
			if(TipoNota.NFO.equals(n.getTipoNota())){
				nota=XML_NOTA_PAPEL;
			}
			
			nota = nota.replace("@serieNota@", n.getSerie())
			.replace("@numeroNota@", n.getNumero())
			.replace("@dataNota@", n.getDate().toString())
			.replace("@pesoNota@", n.getPeso().toString())
			.replace("@valorNota@", n.getValor().toString());
			
			notasReplace+=nota;
		}
		
		
		
		//CTE
		String cte = XML_CTE.replace("@header@", header)
				.replace("@vagoes@",vagoesRepace)
				.replace("@notas@",notasReplace);
		
		cte =formatXML(cte);
		
		log.info(cte);
		
		return cte;
	}

	private String formatXML(String cte)
			throws TransformerFactoryConfigurationError {
		try {
			Source xmlInput = new StreamSource(new StringReader(cte.trim()));
			StringWriter stringWriter = new StringWriter();
			StreamResult xmlOutput = new StreamResult(stringWriter);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", 5);
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			transformer.transform(xmlInput, xmlOutput);
			return xmlOutput.getWriter().toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
