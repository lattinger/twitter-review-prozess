package ch.zhaw.gpi.twitterreview.delegates;

import ch.zhaw.gpi.twitterreview.services.TwitterService;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *implementation des Service Tasks "Tweet senden"
 * 
 * @author Laura
 */
@Named("sendTweetAdapter")
public class SendTweetDelegate implements JavaDelegate {

    /**
     *1. Die Prozessvariable tweetContent wird ausgelesen
     *2. Dieser Text wird in der Konsole ausgegeben
     * 
* Postet einen Tweet mit dem gewünschten Text
*
* @param de Objekt, welches die Verknüpfung zur Process Engine und zur aktuellen Execution enthält
* @throws Exception
*/
@Autowired
private TwitterService twitterService;
@Override
public void execute(DelegateExecution de) throws Exception {
// Prozessvariable tweetContent wird ausgelesen
String tweetContent = (String) de.getVariable("tweetContent");
// Dieser Text wird dem Twitter Service an die Methode updateStatus übergeben
twitterService.updateStatus(tweetContent);
}
}
