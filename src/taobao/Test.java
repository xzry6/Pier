package taobao;

import com.taobao.api.*;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.UserSellerGetResponse;

public class Test {
	protected static String url = "http://gw.api.taobao.com/router/rest";//ɳ�价�����õ�ַ
    //��ʽ������Ҫ����Ϊ:http://gw.api.taobao.com/router/rest
    protected static String appkey = "23184936";
    protected static String appSecret = "c11d6a6140bdb19bb0cde8a1116e2c38";
    protected static String sessionkey = "6102411f92627131aab1152d22f30ce840851ea03fc884b279393316"; //�� ɳ������ʺ�sandbox_c_1��Ȩ��õ���sessionkey
    public static void testUserGet() {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);//ʵ����TopClient��
        UserSellerGetRequest req = new UserSellerGetRequest();//ʵ��������API��Ӧ��Request��
        req.setFields("shawwn,user_id,type");
        //req.setNick("sandbox_c_1");
        UserSellerGetResponse response;
        try {
            response = client.execute(req,sessionkey); //ִ��API���󲢴�ӡ���
            System.out.println("result:"+response.getBody());
         
        } catch (ApiException e) {
        // deal error
        }
    }
    public static void main(String[] args) {
        Test.testUserGet();
    }
}
