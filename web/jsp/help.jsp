<jsp:include page="header.jsp">
    <jsp:param name="title" value="Capita Bay Home"/>
    <jsp:param name="css" value="/css/style.css" />
    <jsp:param name="js" value="/js/main.js" />
</jsp:include>
    <div class="container">
        <div id="help-container" class="content">
            <h1> Help </h1>            
            <h3 id="help-search" class="help-question"><span class="glyphicon glyphicon-chevron-right"></span> How do I search for stocks? </h3>
            <p id="help-search-answ"> You can search for stocks in the header up top by using the search bar. The dropdown to the right of the search bar allows you to search by either stock name or stock type. Alternatively you can view all listed stocks by clicking the 'Trading' link, also located in the header.  </p>
                
            <h3 id="help-history" class="help-question"><span class="glyphicon glyphicon-chevron-right"></span> How do I see how a stock has preformed in the past? </h3>
            <p id="help-history-answ"> After searching a stock, or listing all stocks from the 'Trading' page, click on the stock you are interested in to go to the page for that specific stock. This page will include all relevant information for the stock in question. If you scroll to the bottom of this page, you will see a graph that will show the stock's performance over a set number of months in the past. The default number of months is six; however, you can change this value by inputting your desired number of months into the text box directly below. </p>
                
            <h3 id="help-buy" class="help-question"><span class="glyphicon glyphicon-chevron-right"></span> How do I buy stocks? </h3>
            <p id="help-buy-answ"> After searching a stock, or listing all stocks from the 'Trading' page, click on the stock you are interested in to go to the page for that specific stock. If you are logged in, the top of this page will include a form to buy the stock you are interested in. To select the number of shares you wish to buy, you can either use the slider or the text box below to input your number. The text box next to it will list the price you will pay for the number of shares you selected. Alternatively, you can select the number of shares by specifying the price you wish to pay. The text box listing the number of shares will be automatically updated. Next you select the account from which you want to buy the stock, the type of the order, and the employee you would like to process the order. </p>
            
            <h3 id="help-holdings" class="help-question"><span class="glyphicon glyphicon-chevron-right"></span> How do I view my current stock holdings? </h3>
            <p id="help-holdings-answ"> You can view your current stock holdings by navigating to your customer profile. This can be done by clicking on your name in the top right corner of the header. In your customer profile, there is a 'Current Stock Holdings' tab. Selecting this tab will pull up a list of all of your current stock holdings. </p>
                
            <h3 id="help-sell" class="help-question"><span class="glyphicon glyphicon-chevron-right"></span> How do I sell stocks? </h3>
            <p id="help-sell-answ"> You can sell your currently owned stocks by first navigating to your customer profile. This can be done by clicking on your name in the top right corner of the header. Next you must go to your current stock holding list, to see all the stocks you can potentially sell. In your customer profile, there is a 'Current Stock Holdings' tab. Selecting this tab will pull up a list of all of your current stock holdings. Next click the 'Edit' button to the right of the stock listing you wish to sell, this will bring up the sell stock form. First you must select the number of shares you wish to sell by using the slider at the top of the form. This will automatically populate the sell price, indicating how much money your shares will sell for. Finally select the sell type and click sell. </p>
                
            <h3 id="help-conditional" class="help-question"><span class="glyphicon glyphicon-chevron-right"></span> How do set a conditional order on a stock I own? </h3>
            <p id="help-conditional-answ"> You can set a conditional order on your currently owned stocks by first navigating to your customer profile. This can be done by clicking on your name in the top right corner of the header. Next you must go to your current stock holding list, to see all the stocks you can potentially set a conditional order for. In your customer profile, there is a 'Current Stock Holdings' tab. Selecting this tab will pull up a list of all of your current stock holdings. Next click the 'Edit' button to the right of the stock listing you wish to set a conditional order for, this will bring up the sell stock form. At the top there will be tabs for selecting the type of conditional order you would like to set, either trailing stop or hidden stop. For trailing stop you select the trailing stop price value you wish to sell at. For hidden stop you select the hidden stop percentage value you wish to sell at.  </p>
            
            <h3 id="help-suggestion" class="help-question"><span class="glyphicon glyphicon-chevron-right"></span> How do I get personalized stock suggestions? </h3>
            <p id="help-suggestion-answ"> You can get a list of personalized stock suggestions by first navigating to your customer profile. This can be done by clicking on your name in the top right corner of the header. In your customer profile, there is a 'Stock Suggestion' tab. Selecting this tab will pull up a list of personalize stock suggestions based on your past orders. </p>
        </div>

</div>


<jsp:include page="footer.jsp"/>
