package com.yatra.mailsender.service;

import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;


	public void simpleMailSender(String[] toEmail, String subject, List<String> data) {
		// Check if data is empty or null
		if (data == null || data.isEmpty()) {
			System.out.println("No data to send in the email. Email not sent.");
			return; // Exit the method without sending the email
		}

		String body = String.join("\n", data);

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shivajisangati@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		mailSender.send(message);
		System.out.println("Mail Send...");
	}
	@Autowired
	@Required
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine; 
	}
	@Bean
    public VelocityEngine velocityEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "class");
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        
        return velocityEngine;
	}
	public void sendMimeEmail() {


		    MimeMessagePreparator prep = new MimeMessagePreparator() {

		      @Override
		      public void prepare(MimeMessage mimeMessage) throws Exception {
		        MimeMessageHelper message = new MimeMessageHelper(
		            mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED,
		            "UTF-8");

		        message.setTo("chandrika.b@zapcg.com");
		        message.setFrom("shivajisangati@gmail.com");
		        
		        message.setSubject("CHandrika testing");

		        ClassLoader classLoader = Thread.currentThread()
		            .getContextClassLoader();
		        if (classLoader == null) {
		          classLoader = this.getClass().getClassLoader();
		        }

		        // --Create the HTML body part of the message
		        MimeBodyPart mimeBody = new MimeBodyPart();
		        //String body = VelocityEngineUtils.mergeTemplateIntoString(
		          //  velocityEngine, "templates/invoice.vm", "UTF-8", model);
		        //mimeBody.setContent(body, "text/html");

		        VelocityContext context = new VelocityContext();
		        String response ="{\"data\":{\"specialFareType\":\"\",\"fltSchedule\":{\"DELBOM20240221\":[{\"fid\":\"DELBOM6E531820240221\",\"ID\":\"1\",\"org\":\"DEL\",\"dest\":\"BOM\",\"OD\":[{\"tlot\":\"00:00\",\"fareId\":\"Saver Fare\",\"FS\":[{\"bga\":\"0 kg\",\"ac\":\"6E\",\"oac\":\"\",\"mac\":\"6E\",\"fl\":\"5318\",\"dac\":\"DEL\",\"aac\":\"BOM\",\"ddt\":\"2024-02-21\",\"adt\":\"2024-02-21\",\"dd\":\"16:25\",\"ad\":\"18:40\",\"eq\":\"AIRBUS INDUSTRIE A320-100/200\",\"du\":\"02:15\",\"gdspnr\":\"\",\"rph\":\"1\",\"arpnr\":\"BW4MNB\",\"dct\":\"New Delhi\",\"act\":\"Mumbai\",\"cct\":\"IATA\",\"an\":\"IndiGo\",\"dap\":\"Indira Gandhi\",\"aap\":\"Chatrapati Shivaji\",\"mc\":\"6E\",\"dcc\":\"IN\",\"acc\":\"IN\",\"bag\":\"0 kg\",\"st\":\"Non Stop\",\"ft\":\"Refundable\",\"cabin\":\"Economy\",\"fid\":\"DELBOM6E531820240221\",\"nft\":\"Refundable\",\"dt\":\"T-3\",\"at\":\"T-1\",\"ml\":\"\",\"mlT\":\"2\",\"isGds\":\"false\"}]}]}]},\"pnrMapping\":{\"DEL-BOM\":\"BW4MNB\"},\"fltOrder\":{\"1\":\"DELBOM20240221\"},\"mealLabel\":{\"0\":{\"label\":\"Free Meal\",\"message\":\"\"},\"1\":{\"label\":\"Paid Meal\",\"message\":\"\"},\"2\":{\"label\":\"No Meal Fare\",\"message\":\"This is a special No Meal fare provided by the airline. Meal can only be purchased on board.\"}},\"cityNames\":{\"DEL\":\"New Delhi\",\"BOM\":\"Mumbai\"},\"airportNames\":{\"DEL\":\"Indira Gandhi\",\"BOM\":\"Chatrapati Shivaji\"},\"airlineNames\":{\"6E\":\"IndiGo\"},\"ssrStatus\":{\"0\":{\"Meal\":{\"1|1\":\"\"},\"Baggage\":{\"1.0|-1\":\"\"},\"Others\":{},\"seats\":{\"1|1\":{\"status\":\"Confirmed\",\"FareType\":\"Non-Refundable\",\"seatValue\":\"26A\"}}},\"1\":{\"Meal\":{},\"Baggage\":{},\"Others\":{},\"seats\":{\"1|1\":{\"status\":\"Confirmed\",\"FareType\":\"Non-Refundable\",\"seatValue\":\"26C\"}}},\"2\":{\"Meal\":{},\"Baggage\":{},\"Others\":{},\"seats\":{\"1|1\":{\"status\":\"Confirmed\",\"FareType\":\"Non-Refundable\",\"seatValue\":\"26D\"}}}},\"fareDetails\":{\"ylp\":\"0\",\"DELBOM20240221\":{\"1\":{\"O\":{\"ADT\":{\"bf\":\"7000\",\"tf\":\"7843\",\"px\":\"ADT\",\"qt\":\"2\",\"YQ\":\"336\",\"PSF\":\"91\",\"UDF\":\"61\",\"GST\":\"0\",\"GAST\":\"355\",\"TF\":\"0\",\"WC\":\"0\"},\"CHD\":{\"bf\":\"7000\",\"tf\":\"7843\",\"px\":\"CHD\",\"qt\":\"1\",\"YQ\":\"336\",\"PSF\":\"91\",\"UDF\":\"61\",\"GST\":\"0\",\"GAST\":\"355\",\"TF\":\"0\",\"WC\":\"0\"}}}}},\"refundInfoSlab\":[{\"sector\":\"DEL-BOM\",\"slabs\":{\"ADT\":[{\"starthour\":\"0\",\"endhour\":\"4\",\"cancelAmount\":\"7843\",\"rescheduleAmount\":\"111\"},{\"starthour\":\"4\",\"endhour\":\"96\",\"cancelAmount\":\"3500\",\"rescheduleAmount\":\"3000\"},{\"starthour\":\"96\",\"endhour\":\"698\",\"cancelAmount\":\"3000\",\"rescheduleAmount\":\"9000\"}],\"CHD\":[{\"starthour\":\"0\",\"endhour\":\"4\",\"cancelAmount\":\"7843\",\"rescheduleAmount\":\"111\"},{\"starthour\":\"4\",\"endhour\":\"96\",\"cancelAmount\":\"3500\",\"rescheduleAmount\":\"3000\"},{\"starthour\":\"96\",\"endhour\":\"698\",\"cancelAmount\":\"3000\",\"rescheduleAmount\":\"9000\"}]}}],\"airPnrList\":[\"BW4MNB\"],\"gdsPnrList\":[\"\"],\"supplierList\":[\"6EAPI\"],\"supplierCodeList\":[\"6EAPI\"],\"airlineCodeList\":[\"6E\"],\"wflow\":\"PFB\",\"ftype\":\"O\",\"upSell\":{\"DELBOM20240221\":{\"benefits\":[]}},\"showSaleAlertFailureMessage\":\"true\",\"isSaleAirline\":\"false\",\"saleAlertFailureHeaderMessage\":\"Your payment has been charged however we regret that we are unable to book your tickets with the airline.\",\"saleAlertFailureDescriptionMessage\":\"We have initiated the refund process and the money will be refunded into the account charged. It would take 4-5 business days for the credit to reflect in your account. We sincerely regret any inconvenience this might have caused you.\",\"saleAlertFailureLinkMessage\":\"Please <a href='http://www.yatra.com' />click here</a> to make a fresh booking.\",\"isApprovalPending\":false,\"cancellationInfo\":{\"columnsHeader\":[{\"start\":0,\"end\":4,\"pax\":[\"ADULT\",\"CHILD\"]},{\"start\":4,\"end\":96,\"pax\":[\"ADULT\",\"CHILD\"]},{\"start\":96,\"end\":698,\"pax\":[\"ADULT\",\"CHILD\"]}],\"dataRows\":[{\"sector\":\"DEL-BOM\",\"slabs\":[{\"start\":0,\"end\":4,\"cancellation\":{\"adt\":\"7843\",\"chd\":\"7843\"},\"reschedule\":{\"adt\":\"Not Available\",\"chd\":\"Not Available\"}},{\"start\":4,\"end\":96,\"cancellation\":{\"adt\":\"3500\",\"chd\":\"3500\"},\"reschedule\":{\"adt\":\"3000\",\"chd\":\"3000\"}},{\"start\":96,\"end\":698,\"cancellation\":{\"adt\":\"3000\",\"chd\":\"3000\"},\"reschedule\":{\"adt\":\"9000\",\"chd\":\"9000\"}}]}],\"rescheduleLabels\":{\"cancellation\":\"Different airline\",\"reschedule\":\"Same airline\"},\"refundAvailable\":false}},\"bookingStatus\":\"success\",\"isPrimeSelected\":\"true\",\"bookingDate\":\"23-1-2024\",\"paymentStatus\":\"1\",\"sc\":{\"payProp\":{\"paymentId\":\"36c422b6-a606-41d9-8f78-bf3b3f02d344\",\"superPnr\":\"9079588\",\"bookingId\":\"734a74fc-f951-4259-901b-a26e539b1fff\",\"ttid\":\"119079588\",\"type\":\"FULL\",\"pricingId\":\"59ec60f2-3fd4-428d-904b-1492369cdfa4\",\"voucherAmount\":0.0,\"mode\":\"NetBanking\",\"curr\":\"INR\",\"tenantScope\":\"dom2\"},\"charges\":{\"tfp\":23529,\"gst\":0,\"balAmt\":0,\"convFee\":0,\"meal\":650,\"bag\":900,\"seat\":650,\"other\":0,\"ecash\":0,\"thf\":0,\"amtPaid\":27272,\"total\":27272},\"addons\":{\"TAKE OFF Contribution\":10,\"YTPRIME\":1533},\"addonDetails\":{\"save.child\":{\"code\":\"save.child\",\"label\":\"TAKE OFF Contribution\",\"description\":\"\",\"amount\":10},\"YTPRIME\":{\"code\":\"YTPRIME\",\"label\":\"YTPRIME\",\"description\":\"Prime Subscription\",\"amount\":1533}}},\"passengerList\":[{\"title\":\"Mr\",\"fname\":\"Test\",\"mname\":\"\",\"lname\":\"Test\",\"paxid\":75509467,\"address\":null,\"nomineeName\":\"\",\"tickets\":[],\"reportingDetails\":[],\"paxType\":\"ADULT\",\"paxClass\":\"Adult 0 0\",\"dob\":\"\",\"addons\":{\"bag\":{\"o\":[{\"typ\":\"ABHF\",\"desc\":\"Extra Piece\",\"amt\":\"900\",\"curr\":\"INR\",\"convamt\":\"900\",\"imageUrl\":\"//www.yatra.com/content/b2c-cdn/bongo-checkout-cdn/images/ssricons/extra-piece.png?v=20240123152521\",\"isdata\":true,\"dispAmt\":\"  (INR 900)\",\"$$hashKey\":\"object:2855\",\"pax\":\"0\",\"trip\":\"O\",\"uid\":\"1.0\"}]},\"meal\":{\"o\":[{\"status\":\"\",\"typ\":\"CPML\",\"category\":\"VEG\",\"imageUrl\":\"https://ns.yatracdn.com/common/flights/images/meals/VegMeal_App.png\",\"desc\":\"Corporate Meal\",\"amt\":\"650\",\"curr\":\"INR\",\"convamt\":\"650\",\"isdata\":true,\"dispAmt\":\"  (INR 650)\",\"$$hashKey\":\"object:2871\",\"pax\":\"0\",\"trip\":\"O\",\"uid\":\"1\",\"rph\":\"1\"}]},\"seats\":{\"o\":[{\"status\":\"Confirmed\",\"rph\":\"1\",\"uid\":\"1\",\"seatValue\":\"26A\",\"price\":250,\"isdata\":true,\"pax\":\"0\",\"trip\":\"O\",\"arrloc\":\"BOM\",\"deploc\":\"DEL\"}]},\"ppt\":{\"no\":\"\",\"ic\":\"\",\"cc\":\"\",\"in\":\"\",\"nat\":\"\",\"exp\":{}}}},{\"title\":\"Mrs\",\"fname\":\"Testr\",\"mname\":\"\",\"lname\":\"Test\",\"paxid\":75509468,\"address\":null,\"nomineeName\":\"\",\"tickets\":[],\"reportingDetails\":[],\"paxType\":\"ADULT\",\"paxClass\":\"Adult 1 1\",\"dob\":\"\",\"addons\":{\"bag\":{\"o\":[{\"typ\":\"\",\"desc\":\"\",\"amt\":\"0.0\",\"curr\":\"\",\"ptc\":\"\",\"convamt\":\"0.0\",\"rph\":0,\"uid\":0,\"status\":\"\"}]},\"meal\":{\"o\":[{\"isdata\":false,\"uid\":0,\"rph\":0,\"status\":\"\",\"desc\":\"\"}]},\"seats\":{\"o\":[{\"status\":\"Confirmed\",\"rph\":\"1\",\"uid\":\"1\",\"seatValue\":\"26C\",\"price\":250,\"isdata\":true,\"pax\":\"1\",\"trip\":\"O\",\"arrloc\":\"BOM\",\"deploc\":\"DEL\"}]},\"ppt\":{\"no\":\"\",\"ic\":\"\",\"cc\":\"\",\"in\":\"\",\"nat\":\"\",\"exp\":{}}}},{\"title\":\"Master\",\"fname\":\"Testw\",\"mname\":\"\",\"lname\":\"Test\",\"paxid\":75509469,\"address\":null,\"nomineeName\":\"\",\"tickets\":[],\"reportingDetails\":[],\"paxType\":\"CHILD\",\"paxClass\":\"Child 2 0\",\"dob\":\"\",\"addons\":{\"bag\":{\"o\":[{\"typ\":\"\",\"desc\":\"\",\"amt\":\"0.0\",\"curr\":\"\",\"ptc\":\"\",\"convamt\":\"0.0\",\"rph\":0,\"uid\":0,\"status\":\"\"}]},\"meal\":{\"o\":[{\"isdata\":false,\"uid\":0,\"rph\":0,\"status\":\"\",\"desc\":\"\"}]},\"seats\":{\"o\":[{\"status\":\"Confirmed\",\"rph\":\"1\",\"uid\":\"1\",\"seatValue\":\"26D\",\"price\":150,\"isdata\":true,\"pax\":\"2\",\"trip\":\"O\",\"arrloc\":\"BOM\",\"deploc\":\"DEL\"}]},\"ppt\":{\"no\":\"\",\"ic\":\"\",\"cc\":\"\",\"in\":\"\",\"nat\":\"\",\"exp\":{}}}}],\"user\":{\"username\":\"Raja Tyagi\",\"email\":\"revelev232@ibtrades.com\",\"isd\":\"91\",\"mobile\":\"7533966366\"},\"webcheckin\":{\"6E_DEL#BOM#2024-02-21\":\"\"},\"ewalletinfo\":{\"balance\":0.0,\"ewalletOff\":\"false\"},\"metaParams\":{},\"primeDetails\":{\"planName\":\"YTPrime\",\"totalAmount\":1533.0,\"validity\":\"6\",\"noOfBookingsAllowed\":\"6\",\"createdDt\":\"2023-05-25 11:50:52\",\"planExpiryDate\":\"2024-11-23 06:03:31\",\"primePageBenefits\":[{\"benefitName\":\"Access to Special Fare\",\"benefitDescription\":\"Access to Special Fares\",\"benefitIcon\":\"https://ns.yatracdn.com/common/flights/images/primebenefits/acess_to_special_fare.png\",\"benefitStatus\":\"ACTIVE\",\"anyProperties\":{\"anyProperties\":{}}},{\"benefitName\":\"No Convenience Fee\",\"benefitDescription\":\"No Convenience Fee (over and above ongoing offers) on Domestic Flights\",\"benefitIcon\":\"https://ns.yatracdn.com/common/flights/images/primebenefits/no_convience_fees.png\",\"benefitStatus\":\"ACTIVE\",\"anyProperties\":{\"anyProperties\":{}}},{\"benefitName\":\"Milestone Rewards\",\"benefitDescription\":\"Milestone rewards\",\"benefitIcon\":\"https://ns.yatracdn.com/common/flights/images/primebenefits/milestone_rewards.png\",\"benefitStatus\":\"ACTIVE\",\"anyProperties\":{\"anyProperties\":{}}},{\"benefitName\":\"Exclusive Bank Offers\",\"benefitDescription\":\"Exclusive Bank Offers\",\"benefitIcon\":\"https://ns.yatracdn.com/common/flights/images/primebenefits/exclusive_bank_offers.png\",\"benefitStatus\":\"ACTIVE\",\"anyProperties\":{\"anyProperties\":{}}},{\"benefitName\":\"VIP Customer Support\",\"benefitDescription\":\"VIP customer support\",\"benefitIcon\":\"https://ns.yatracdn.com/common/flights/images/primebenefits/vip_customer_support.png\",\"benefitStatus\":\"ACTIVE\",\"anyProperties\":{\"anyProperties\":{}}},{\"benefitName\":\"Weekly Surprise Gifts\",\"benefitDescription\":\"Surprise gifts every week\",\"benefitIcon\":\"https://ns.yatracdn.com/common/flights/images/primebenefits/weekly_surprise_gifts.png\",\"benefitStatus\":\"ACTIVE\",\"anyProperties\":{\"anyProperties\":{}}}]},\"departDate\":\"2024-02-21\",\"searchPageUrl\":\"https://dev.yatra.com/air-search-ui/dom2/trigger?type=O&viewName=normal&flexi=0&noOfSegments=1&ADT=2&CHD=1&INF=0&class=Economy&origin=DEL&destination=BOM&flight_depart_date=21/02/2024&unique=1706005059103&airFailedTtid=119079588\",\"ciParam\":\"\",\"isReschd\":false,\"isMultiTicket\":false,\"org\":\"DEL\",\"dest\":\"BOM\",\"uiUtils\":{\"impNotes\":[]},\"isSTU\":false,\"airFailedData\":{},\"searchCriteria\":{\"id\":1293274337,\"searchId\":\"5e1c053a-10f2-4f3d-a1e2-ea342b4d3e34\",\"sessionId\":\"3dfef641-f7d3-4cce-9722-41b9bfe21c15\",\"userId\":100518256,\"origin\":\"DEL\",\"originCountry\":\"IN\",\"dest\":\"BOM\",\"destCountry\":\"IN\",\"adults\":2,\"children\":1,\"infants\":0,\"tripType\":\"O\",\"flightType\":\"DOM\",\"daysArdChkd\":\"N\",\"searchCount\":82,\"travelClass\":\"E\",\"departDate\":\"Feb 21, 2024 12:00:00 PM\",\"createdOn\":\"Jan 23, 2024 3:41:58 PM\",\"tenantId\":17,\"clientIPAddress\":\"10.11.11.0\",\"hostServerIPAddress\":\"101114146\",\"userAgentInfo\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36\",\"browserInfo\":\"Chrome 120.0.0.0\",\"masterSearchId\":\"5e1c053a-10f2-4f3d-a1e2-ea342b4d3e34\",\"loggedIn\":1,\"pageFromCache\":0},\"showAirfailedUI\":false,\"airFailedProcessed\":false}";
		        context.put("confirmationResponseJson", response);
		        StringWriter stringWriter = new StringWriter();
		        velocityEngine.mergeTemplate("/templates/newEmailConfirmation.vm", "UTF-8", context, stringWriter);
		        String body = stringWriter.toString();
		        
		        
		        mimeBody.setContent(body, "text/html");
		        mimeBody.setHeader("Content-Length", String.valueOf(body.length()));
		        
		        System.out.println(mimeBody.getContent());
		        MimeBodyPart mimeImage = new MimeBodyPart();
		        mimeImage.setHeader("Content-ID", "myLogo");
		        
		        Multipart multipart = new MimeMultipart();
		        multipart.addBodyPart(mimeBody);
		        multipart.addBodyPart(mimeImage);  
		        mimeMessage.setContent(multipart);
		      }
		    };
		    mailSender.send(prep);
		  }
}
