package no.ntnu.item.trainvisualsystem.trainconnectionstation;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.bitreactive.library.amqp.AmqpError;
import com.bitreactive.library.amqp.AmqpMessage;
import com.bitreactive.library.amqp.BrokerAddress;
import com.bitreactive.library.amqp.Utils;
import com.bitreactive.library.amqp.brokeredamqpclient.BrokeredAMQPClient;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.trainvisualsystem.Constants;

public class TrainConnectionStation extends Block {

	private String host, queueName;
	private int port = Utils.DEFAULT_AMQP_PORT;
	

	public String error(String e) {
		return e;
	}
	
	public String properties(Properties p) {
		StringBuffer sb = new StringBuffer();
		if (p.containsKey(Constants.P_AMQP_BROKER_HOST)) {
			host = p.getProperty(Constants.P_AMQP_BROKER_HOST);
		} else {
			sb.append("Missing property AMQP-Broker-Host!\n");
		}
		if (p.containsKey(Constants.P_AMQP_BROKER_PORT)) {
			String t = p.getProperty(Constants.P_AMQP_BROKER_PORT);
			try {
				port = Integer.parseInt(t);
			} catch (NumberFormatException e) {
			}
		}
		if (p.containsKey(Constants.P_AMQP_BROKER_QUEUE_NAME)) {
			queueName = p.getProperty(Constants.P_AMQP_BROKER_QUEUE_NAME);
		} else {
			sb.append("Missing property AMQP-Broker-Queue-Name!\n");
		}
		if (sb.toString().isEmpty()) {
			logger.info("Properties for Remote Monitroing System: amqpBrokerHost=" + host 
					+ ", amqpBrokerPort=" + port 
					+ ", amqpBrokerQueueName=" + queueName);
			return null;
		}
		return sb.toString();
	}


	public BrokeredAMQPClient.Parameter init() {
		logger.info("About to start AMQP Client...");
		Set<BrokerAddress> addr = new HashSet<BrokerAddress>();
		addr.add(new BrokerAddress(host, port, queueName));
		return new BrokeredAMQPClient.Parameter(addr);
	}

	public void ready() {
		logger.info("Remote Monitoring System Ready to start Train Visualizaton !");
	}

	public String amqpErr(AmqpError e) {
		if (e.message != null) {
			
			return (e.errorMessage + " for AMQP Received message, Train Information= " + e.message.body) ;
		} else {
			logger.error(e.errorMessage);
			return (e.errorMessage);
		}
	}
	


	public String receivedTrainInformation(AmqpMessage m) {
		//logger.info("Train Details received, Train Started At=" + m.creationTime.toString() + ", " + m.subject + " = " + m.body);
		
		return (m.body);
	}

	

	

		

}
