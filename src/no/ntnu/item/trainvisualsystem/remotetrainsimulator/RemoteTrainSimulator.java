package no.ntnu.item.trainvisualsystem.remotetrainsimulator;

import java.util.Properties;

import javax.swing.JOptionPane;

import no.ntnu.item.trainvisualsystem.Constants;
import com.bitreactive.library.amqp.AmqpError;
import com.bitreactive.library.amqp.AmqpMessage;
import com.bitreactive.library.amqp.Utils;
import com.bitreactive.library.amqp.AmqpMessage.SendProperty;
import com.bitreactive.library.amqp.brokeredamqpclient.BrokeredAMQPClient;
import no.ntnu.item.arctis.runtime.Block;

public class RemoteTrainSimulator extends Block {
	private String host, queueName, subject = "Subject";
	private int port = Utils.DEFAULT_AMQP_PORT;

	public String properties(Properties p) {
		StringBuffer sb = new StringBuffer();
		
		if (p.containsKey(Constants.P_AMQP_BROKER_HOST)) {
			host = p.getProperty(Constants.P_AMQP_BROKER_HOST);
		} else {
			sb.append("Missing AMQP-Broker-Host!\n");
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
		if (p.containsKey(Constants.P_AMQP_MESSAGE_SUBJECT)) {
			subject = p.getProperty(Constants.P_AMQP_MESSAGE_SUBJECT);
		}
		if (sb.toString().isEmpty()) {
			logger.info("Properties for Position Publisher: amqpBrokerHost=" + host + ", amqpBrokerPort=" + port
					+ ", amqpBrokerQueueName=" + queueName + ", amqpMessageSubject=" + subject);
			return null;
		}
		return sb.toString();
	}
	

	public BrokeredAMQPClient.Parameter init() {
		logger.info("About to start AMQP Client...");
		return new BrokeredAMQPClient.Parameter();
	}

	public AmqpMessage trainInfo(String trainInfo) {
		AmqpMessage m = new AmqpMessage(host, port, queueName, subject, trainInfo);
		m.sendProperty = new SendProperty(2000);   
		return m;
	}

	public String published(AmqpMessage m) {
		logger.info("Train Started At -" + m.creationTime.toString() + ", " + m.subject + " = " + m.body);
		return ("Train Started At ")+ m.creationTime.toString() +", Current Properties =" + m.body;
	}

	public void amqpErr(AmqpError e) {
		if (e.message != null) {
			logger.error(e.errorMessage + " for AMQP message, content= " + e.message.body);
		} else {
			logger.error(e.errorMessage);
		}
	}

	public String error(String e) {
		logger.error(e);
		return e;
	}

	public void simulatorErr(String Err) {
		
		JOptionPane.showMessageDialog(null,
			    "Error initializing Train Simulator! \n" + Err,
			    "Train Simulator Error",
			    JOptionPane.ERROR_MESSAGE);
		
		
	}

	public String titleConsole() {
		return "Train Information Published to Monitoring Centre";
	}



	

	

	
}
