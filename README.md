# SwatiAhuja_Java

ABC Investment Bank is a prominent global bank, as part of the General Ledger System, you are required to develop a position calculation process.

Position Calculation process takes start of day positions and transaction files as input, apply transactions on positions based on transaction type (B/S) and account type (I/E), and computes end of day positions. Depending on the direction of the transaction (Buy/Sell) each transaction is recorded as debit and credit into external and internal accounts in the “Positions” file.

 

Input:

Positions File: contain start positions for instruments

Transactions Files: contains transactions for a day

 

Process:

Read Positions and Transactions files, compute new positions and write to new end of day positions file.

For each transaction in Transaction file,

Apply TransactionQuantity into matching instrument records in the position file

If Transaction Type =B ,

                                For AccountType=E, Quantity=Quantity + TransactionQuantity

                                For AccountType=I, Quantity=Quantity - TransactionQuantity

If Transaction Type =S ,

                                For AccountType=E, Quantity=Quantity - TransactionQuantity

                                For AccountType=I, Quantity=Quantity + TransactionQuantity

 

Query:

·         At the end of the process find instruments with largest and lowest net transaction volumes for the day. (net volume is change in positions from start of day positions to end of day positions)

 

 

Attached files:

Input#

1. Input_StartOfDay_Positions.txt  (csv format, comma delimited, 1st line is header line)

2. Input_Transactions.txt (json format)  (use json library of your choice)

 

Expected Output: Expected_EndOfDay_Positions.txt (Output of the process should match with this Expected output)

 

 

Data Dictionary:

 

Transaction File

TransactionId

Id of transaction record

Instrument

Stock symbol of the instrument

TransactionType

B (Buy) / S (Sell)

TransactionQuantity

Quantity of transaction

 

 

Positions File

Instrument

Stock symbol of the instrument

Account

Ledger Account Number

AccountType

Account type I (Internal) / E (External)

Quantity

Total postion quantity

 

 

EndOfDay_Positions

Instrument

Stock symbol of the instrument

Account

Ledger Account Number

AccountType

Account type I (Internal) / E (External)

Quantity

Total postion quantity

Delta

Net change in positions during the day

 

 

Criteria:

Coding standard

Unit tests

Exception handling

 

 

Useful links:

https://www.investopedia.com/terms/p/position.asp

https://www.investopedia.com/terms/g/generalledger.asp

 

 


Visit our website at http://www.ubs.com

This message contains confidential information and is intended only 
for the individual named.  If you are not the named addressee you 
should not disseminate, distribute or copy this e-mail.  Please 
notify the sender immediately by e-mail if you have received this 
e-mail by mistake and delete this e-mail from your system.
	
E-mails are not encrypted and cannot be guaranteed to be secure or 
error-free as information could be intercepted, corrupted, lost, 
destroyed, arrive late or incomplete, or contain viruses.  The sender 
therefore does not accept liability for any errors or omissions in the 
contents of this message which arise as a result of e-mail transmission.  
If verification is required please request a hard-copy version.  This 
message is provided for informational purposes and should not be 
construed as a solicitation or offer to buy or sell any securities 
or related financial instruments.

 
UBS reserves the right to retain all messages. Messages are protected
and accessed only in legally justified cases.