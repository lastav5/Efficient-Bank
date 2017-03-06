# Efficient-Bank
Final project in Data Structures and Algorithms course. Mainly demonstrates use of red-black trees and some OOP principles.<br />
A bank containing customers, their id and balance. Functionality includes adding/removing customers, getting and updating a customer's balance, printing all customers with negative balance and printing the customer with the highest balance. All is done in a low complexity using two binary tress to represent the bank.<br /><br />
Project's paper has been copy-pasted:<br /><br />
<div dir="rtl">

* מספר לקוח\חשבון =  .accountId מספר זהות =  .customerId  <br />

מערכת הבנק מורכבת מהמחלקות הבאות:  <br /><br />
Customer  - מחלקה המייצגת לקוח בבנק ומכילה את תכונות הלקוח -  <br />
שם -name <br />
מספר תעודת זהות -CustomerId  <br />
מספר לקוח -AccountId  <br />
יתרה -balance  <br /><br />
RBTree  - מחלקה אבסטרקטית המייצגת עץ אדום שחור ומכילה תכונת root המצביעה לראש העץ ותכונת nil סטטית המוסכמת בעצים אדומים שחורים. בנוסף מכילה פעולות המשותפות לשני העצים balanceTree ו AccountIdTree והןDelete , DeleteFixup, LeftRoate, RightRotate, InsertFixup. כמו כן, פעולות כלליות יותר -TreeMinimum, TreeMaximum, TreeSuccessor.  <br /><br />
BalanceTree  - יורשת מ RBTree ומייצגת עץ אדום שחור המנהל את הלקוחות ע"פ יתרה. מכילה פעולות ספציפיות לעץ זה - הכנסה של איבר (ע"פ יתרה).  <br /><br />
AccountIdTree  - יורשת מ RBTree ומייצגת עץ אדום שחור המנהל את הלקוחות ע"פ מספר חשבון (מס לקוח). מכילה פעולות ספציפיות לעץ זה - הכנסה של איבר (ע"פ מספר חשבון), חיפוש איבר (ע"פ מספר חשבון).  <br /><br />
Bank  - מחלקה המייצגת את הבנק שלנו. מכילה כתכונות את שני העצים -balanceTree, accountIdTree. כמו כן מכילה את פעולות השאילתות וההודעות המפורטות במטלה - עידכון יתרה, הוספת לקוח חדש, מחיקת לקוח, בדיקת יתרה, שליפת הלקוח בעל היתרה הגבוהה ביותר, הדפסת כל הלקוחות בעלי יתרה שלילית.  <br /><br />
Node  - מייצג איבר בעץ אדום שחור. בעל התכונות המוסכמותcolor, left, right, p. בנוסף מכיל את התכונות customer המצביע לאובייקט הלקוח שלו ו parallel המצביע ל node המתאים לו (ה node המכיל את אותו אובייקט לקוח) בעץ השני.  <br /><br />
Main - המחלקה בעלת הפעולה הסטטית המשמשת לתחילת הרצת הקוד.  <br /><br />
פעולות שינוי ושאילתות:  <br /><br />
UpdateBalanceByAccountId - עדכון יתרה של לקוח ע"פ מספר החשבון(לקוח).  <br />
השגרה מקבלת את הסכום שיש להוסיף ליתרה (יכול להיות חיובי או שלילי) ואת מספר החשבון שאת יתרתו מעדכנים. נחפש את הלקוח בעץ accountIdTree ונפנה אל ה parallel שלו - זהו איבר הלקוח בעץbalanceTree . נעדכן את היתרה של אובייקט הלקוח (נשמור בידינו אובייקט זה). כעת עץ היתרות עלול להפר את התכונות של עץ חיפוש בינארי. נמחק כל איבר מהעץ שלו וניצור 2 node-ים חדשים וריקים בעלי ה customer ששמרנו קודם. כעת נדאג שכל parallel יצביע ל node המתאים - כלומר ה node-ים יצביעו "אחד לשני" (כל parallel מצביע ל node הנגדי). כל שנותר הוא להוסיף את ה node-ים לעץ ע"י insert.  <br />
•	חיפוש איבר בעץ, מחיקת איבר והוספת איבר הן פעולות בעלות זמן ריצה O(lg n) ולכן זמן הריצה של שגרה זו הוא (O(lg n.  <br /><br />
 - AddNewCustomer הוספת לקוח חדש.  <br />
השגרה מקבלת את אובייקט הלקוח שיש להוסיף. ניצור 2 אובייקטים של node חדשים וריקים לחלוטין חוץ מתכונת customer שתצביע לאובייקט הלקוח שקיבלנו. נעדכן את תכונת ה parallel של כל אחד מהם כך שכל parallel יצביע ל node ה"נגדי" - ה parllel של node של העץ balanceTree יצביע ל node של העץ accountIdTree וההיפך. נכניס את המתאים לעץ balanceTree ואת השני לעץaccountIdTree .  <br />
•	הוספת כל אחד מה node-ים לעצים המתאימים - O(lgn) ולכן זהו זמן ריצת השגרה כולה.  <br /><br />
 - RemoveCustomerByAccountId מחיקת לקוח ע"פ מספר חשבון.  <br />
השגרה מקבלת את מספר החשבון של הלקוח העוזב. נחפש את ה node של הלקוח בעץ accountIdTree ונבדוק אם יתרתו אפס. אם כן - נוכל להמשיך במחיקה. אם לא - נדאג שהלקוח יאפס את יתרתו. נקרא לשגרה updateBalanceByAccountId ונשלח לה סכום כסף השווה למינוס היתרה הקיימת - שכן זהו הסכום שיש להוסיף על מנת לאפס את היתרה. כזכור, השגרה updateBalanceByAccountId מוחקת את הלקוחות מהעץ ומוסיפה אותם מחדש (עם-node ים חדשים בעלי כתובת חדשה). לכן נחפש שוב את ה node המתאים ע"י accountId. נשמור גם את ה parallel שלו (זהו ה node בעץ balanceTree) וכעת כששני ה node-ים בידינו נמחק כל אחד מהעץ שלו.  <br />
•	חיפוש איבר בעץ ומחיקה של איבר מעץ - כל אחד בעל סיבוכויות O(lg n) וכל אחד קורה מספר קבוע של פעמים. לכן (O(lgn זהו זמן הריצה של השגרה כולה.  <br /><br />
PrintBalancebyAccountId  - הדפסת יתרה של לקוח ע"פ מספר חשבון.  <br />
השגרה מקבלת מספר חשבון של לקוח. נחפש את הלקוח בעץ accountId ע"פ מספר החשבון ואז נפנה לתכונת ה customer שלו - אובייקט הלקוח - ואל תכונת ה balance שלו. נדפיס את היתרה.  <br />
•	חיפוש בעץ - ( O(lgnולכן זהו זמן הריצה של השגרה כולה.  <br /><br />
PrintMaxBalanceCustomer  - הדפסת פרטי הלקוח בעל היתרה הגבוהה ביותר.  <br />
נחפש את הלקוח בעל היתרה הגבוהה ביותר בעץ היתרות ע"י השגרה הקיימתTreeMaximum  (העץ הוא עץ חיפוש בינארי ולכן הלקוח הנדרש יהיה העלה הימני ביותר.) נדפיס את היתרה. <br />
•	זמן ריצת השגרה TreeMinimum הוא O(lgn) ולכן זהו זמן ריצת השגרה כולה.  <br /><br />
PrintNegativeBalanceCustomers  - הדפסת כל הלקוחות בעלי יתרה שלילית.  <br />
נבדוק שהעץ לא ריק ואז נקרא לשגרה printNeg הרקורסיבית - זוהי שגרת עזר שפועלת בדומה לסריקתinOrder . כידוע, סריקה תוכית של עץ חיפוש בינארי תיתן רשימה של ערכי העץ בסדר עולה. לכן בכל שלב בסריקה נבדוק אם ערך האיבר שלילי. אם כן - נדפיס את פרטי הלקוח של איבר זה. ונמשיך בסריקה לעבר תת העץ הימני. אחרת - האיבר הוא חיובי ולכן לא נטרח להמשיך בסריקה ולא נפנה לתת העץ הימני.  <br />
•	מתבצעת סריקה תוכית של כל האיברים בעץ (במקרה הגרוע) ולכן זמן הריצה הוא ( O(n.  <br />
</div>
