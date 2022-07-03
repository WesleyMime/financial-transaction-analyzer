package br.com.fta.transaction;

import java.io.InputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import br.com.fta.transaction.exceptions.InvalidTransactionException;
import br.com.fta.user.Mapper;

public class TransactionMapperXML implements Mapper<InputStream, List<Transaction>>{

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> map(InputStream source) {
		try {
			XStream xstream = new XStream();
			xstream.allowTypesByWildcard(new String[]{"br.com.fta.**"});
			xstream.alias("transactions", List.class);
			xstream.alias("transaction", Transaction.class);
			return (List<Transaction>) xstream.fromXML(source);

		} catch (RuntimeException e) {
			throw new InvalidTransactionException();
		}
	}

}
