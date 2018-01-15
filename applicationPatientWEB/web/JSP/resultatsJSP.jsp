<%-- 
    Document   : resultatsJSP
    Created on : 15-janv.-2018, 13:14:52
    Author     : Nicolas
--%>

<%@page import="java.util.List"%>
<%@page import="ws.Analyses"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="fr-BE">

<head>

	<meta charset="UTF-8" />
	
	<!-- SEO -->
	<title>Laboratoire BNSF</title>

	<!-- Social & Open Graph -->
	<meta property="og:title" content="Split - HTML Template Demo" />
	<meta property="og:description" content="Split is a centrally-divided layout for a professional online presence with a big image or video left with alongside content." />
	<meta property="og:image" content="https://demo.onepagelove.com/fullsingle-html/split/images/social.jpg"> <!-- include your hosted image full URL -->
	<meta property="og:url" content="https://demo.onepagelove.com/fullsingle-html/split/" />

	<!-- Favicon -->
	<link rel="icon" type="image/png" href="images/favicon.png" sizes="32x32">

	<!-- Styles -->
	<link rel="stylesheet" href="/applicationPatientWEB/CSS/split.css" type="text/css" media="screen" />
	<meta name="viewport" content="width=device-width,initial-scale=1" />
        <script src="https://www.paypalobjects.com/api/checkout.js"></script>
        <!-- Load the required components. -->
        <script src="https://js.braintreegateway.com/web/3.28.0/js/client.min.js"></script>
        <script src="https://js.braintreegateway.com/web/3.28.0/js/paypal-checkout.min.js"></script>

</head>

<body id="fullsingle" class="page-template-page-fullsingle-split">
<%
List<Analyses> listeAnal = (List<Analyses>) request.getAttribute("ListeAnalyses");
%>
    <div class="fs-split">
	<!-- Image Side -->
	<div class="split-image"></div>
	<!-- Content Side -->
	<div class="split-content">
            <div class="split-content-vertically-center">
		<div class="split-intro">
                    <h1>Laboratoire BNSF agréé</h1>
                    <span class="tagline">Prise de sang. Anlyses. Vaccins.</span>
                </div>
                <div class="split-bio">
                    <p>Consultez vos résultats d'analyse rapidement et facilement en un seul clic. Vous pouvez également éffectuer le paiement facilement à l'aide de payPal. </p>
                     <form action="/applicationPatientWEB/Main" method="post">
                        Entrez votre numéro de demande :<br>
                        <input type="text" style="text-align:center;" align="center" name="numDemande" value=""><br><br>
                        <input id="type" type="hidden" name="type" value="resultats">
                        <input type="submit" style="text-align:center;" align="center" value="Obtenir Résultats">
                    </form>
                    
                    <% if(listeAnal != null)
                    {
                    out.println("<div class=\"box effect1\">");
                    for(int i = 0; i<listeAnal.size(); i++){
                     out.println("<p>" + listeAnal.get(i).getItem() + " -> " + listeAnal.get(i).getValeur() + "</p>" );
                    }
                    out.println("</div>");
                    }%>
                    <div id="paypal-button"></div>

                    <script>
                      paypal.Button.render({
                        env: 'sandbox', // Or 'production',

                        commit: true, // Show a 'Pay Now' button

                        style: {
                          color: 'gold',
                          size: 'small'
                        },

                        payment: function() {
                          /* 
                           * Set up the payment here 
                           */
                            // Set up a url on your server to create the payment
                            var CREATE_URL = 'http://aragnac.pythonanywhere.com/payment/create';
                            // Make a call to your server to set up the payment
                            return paypal.request.post(CREATE_URL).then(function(res) {
                                return res.paymentID;
                            });
                        },

                        onAuthorize: function(data, actions) {
                          /* 
                           * Execute the payment here 
                           */
                          // Set up a url on your server to execute the payment
                            var EXECUTE_URL = 'http://aragnac.pythonanywhere.com/payment/execute';
                            // Set up the data you need to pass to your server
                            var data = {
                                paymentID: data.paymentID,
                                payerID: data.payerID
                            };
                            // Make a call to your server to execute the payment
                            return paypal.request.post(EXECUTE_URL, data).then(function (res) {
                                console.log(res);
                                window.alert("payment executed");
                            });
                        },
                        onCancel: function(data, actions) {
                        /* 
                         * Buyer cancelled the payment 
                         */
                      },

                      onError: function(err) {
                        /* 
                         * An error occurred during the transaction 
                         */
                      }
                    }, '#paypal-button');
                  </script>
                </div>
                <div class="split-lists">
                    <div class="split-list">
                        <h3>Connect</h3>
                        <ul>
                            <li><a href="#">Blog</a></li>
                            <li><a href="#">Email</a></li>
                            <li><a href="#">Newsletter</a></li>
                        </ul>
                    </div>
                    <div class="split-list">
                        <h3>Social</h3>
                        <ul>
                            <li><a href="#">Twitter</a></li>
                            <li><a href="#">Instagram</a></li>
                            <li><a href="#">Dribbble</a></li>
                        </ul>
                    </div>
                    <div class="split-list">
                        <h3>Network</h3>
                        <ul>
                            <li><a href="#">Link One</a></li>
                            <li><a href="#">Link Two</a></li>
                            <li><a href="#">Link Three</a></li>
			</ul>
                    </div>
		</div>
                <div class="split-credit">
                    <p>&copy;2017 <a href="#">BNSF@Lab</a> - <a href="https://onepagelove.com/split">Split Template</a> by <a href="https://onepagelove.com">One Page Love</a></p>

				<!-- 
				To edit this credit you can remove the CC3.0 license for only $5 here: https://onepagelove.com/split 
				this really helps contribute towards us developing more templates and means the world to me!
				Cheers, Rob (@robhope)
				-->
                </div>		
            </div>
        </div>
    </div>
</body>
</html>
