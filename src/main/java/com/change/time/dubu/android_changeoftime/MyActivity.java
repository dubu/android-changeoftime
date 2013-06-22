package com.change.time.dubu.android_changeoftime;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.dubu.android_changeoftime.R;

import java.util.*;

public class MyActivity extends Activity {
    public enum Changes {

        // 벼락뇌
        P100100("100100","중뢰")
        ,P110100("110100","뇌택")
        ,P111100("111100","뇌천")
        ,P101100("101100","뇌화")

        ,P011100("011100","뇌풍")
        ,P001100("001100","뇌산")
        ,P000100("000100","뇌지")
        ,P010100("010100","뇌수")

        // 연못택
        ,P100110("100110","택뢰")
        ,P110110("110110","택산")
        ,P111110("111110","택천")
        ,P101110("101110","택화")

        ,P011110("011110","택풍")
        ,P001110("001110","택산")
        ,P000110("000110","택지")
        ,P010110("010110","택수")

        // 하늘천
        ,P100111("100111","천뢰")
        ,P110111("110111","천택")
        ,P111111("111111","중천")
        ,P101111("101111","천화")

        ,P011111("011111","천풍")
        ,P001111("001111","천산")
        ,P000111("000111","천지")
        ,P010111("010111","천수")

        // 불화
        ,P100101("100101","화뢰")
        ,P110101("110101","화택")
        ,P111101("111101","화천")
        ,P101101("101101","중화")

        ,P011101("011101","화픙")
        ,P001101("001101","화산")
        ,P000101("000101","화지")
        ,P010101("010101","화수")

        // 바람풍
        ,P100011("100011","풍뢰")
        ,P110011("110011","풍택")
        ,P111011("111011","풍천")
        ,P101011("101011","풍화")

        ,P011011("011011","중풍")
        ,P001011("001011","풍산")
        ,P000011("000011","풍지")
        ,P010011("010011","풍수")

        // 뫼산
        ,P100001("100001","산뢰")
        ,P110001("110001","산택")
        ,P111001("111001","산천")
        ,P101001("101001","산화")

        ,P011001("011001","산풍")
        ,P001001("001001","중산")
        ,P000001("000001","산지")
        ,P010001("010001","산수")

        // 땅지
        ,P100000("100000","지뢰")
        ,P110000("110000","지택")
        ,P111000("111000","지천")
        ,P101000("101000","지화")

        ,P011000("011000","지풍")
        ,P001000("001000","지산")
        ,P000000("000000","중지")
        ,P010000("010000","지수")

        // 물수
        ,P100010("100010","수뢰")
        ,P110010("110010","수택")
        ,P111010("111010","수천")
        ,P101010("101010","수화")

        ,P011010("011010","수풍")
        ,P001010("001010","수산")
        ,P000010("000010","수지")
        ,P010010("010010","중수");

        int val;
        String name;
        String word;
        String desc;

        DubuChanges up;
        DubuChanges down;

        Changes(String name, String word) {
            this.name = name;
            this.val = convertTenTobitValue(name);
            this.word = word;

            int upVal = Integer.valueOf(name)%1000;
            int downVal = Integer.valueOf(name)/1000;

            this.up = DubuChanges.findName(String.format("%03d", upVal));
            this.down = DubuChanges.findName(String.format("%03d", downVal));
        }

        int convertTenTobitValue(String str) {

            int tenValue =  Integer.valueOf(str);
            int val = 0;

            val = val + tenValue / 100000 * 32;
            tenValue = tenValue % 100000;

            val = val + tenValue / 10000 * 16;
            tenValue = tenValue % 10000;

            val = val + tenValue / 1000 * 8;
            tenValue = tenValue % 1000;

            val = val + tenValue / 100 * 4;
            tenValue = tenValue % 100;

            val = val + tenValue / 10 * 2;
            tenValue = tenValue % 10;

            val = val + tenValue;

            return val;
        }

        public static Changes findName(String name){
            for (int i =0 ; i < Changes.values().length ; i++){
                if(Changes.values()[i].name.equals(name)){
                    return Changes.values()[i];
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return this.name + " " +
                    this.word + " " +
                    this.desc ;
        }
    }

    public enum DubuChanges  {
        // 벼락뇌 변화 변동 , 정신없이 행하다.. 용이 승천하는 맑은 상승
        P100("100","벼락뇌 변화 변동 , 정신없이 행하다. 용이 승천하는 맑은 상승")
        // 연못택 양의 실체화 되어 보이다. 변화의 끝. 모임. 결실, 양극의 이전이라 조심하라.
        ,P110("110","연못택 양의 실체화 되어 보이다. 변화의 끝. 모임. 결실, 양극의 이전이라 조심하라")
        // 하늘건 양의끝 건실. 쌓아둔 양이 최대인 상태. 고요. 정한 상태.
        ,P111("111","하늘천 양의끝 건실")
        // 불화  화려한 불 쌓아둔 장작이 많아 불이 빛을 발하다 이별, 남쪽, 식다. 홧토불. 따듯한 온기
        ,P101("101","화려한 불 쌓아둔 장작이 많아 불이 빛을 발하다 이별, 남쪽. 식다. 홧토불. 따듯한 온기")

        // 바람풍  바람따라 유순히 흘러가다. 찬기운. 시원함.
        ,P001("001","바람풍  바람따라 유순히 흘러가다")
        // 뫼산 정지, 음극의 이전이라 조심하라. 상승 기운이 더할 수록 산이 높아져 건다.
        ,P011("011","뫼산 정지, 음극의 이전이라 조심하라.")
        // 땅지 음의끝 유순 음의 끝 더하는 음이 적어도 쌓아둔 음은 최대인 상태. 평정한 상태. 정한 상태. 출정. 다시 시작
        ,P000("000","땅지 음의끝 유순")
        // 물, 험난, 북쪽, 얼음 녹는다.
        ,P010("010","물, 험난, 북쪽");

        int id;
        String name;
        int val;

        String korMean;
        String engMean;

        String desc;

        DubuChanges(String name,  String desc) {
            this.name = name;
            this.val = convertTenTobitValue(name);
            this.desc = desc;
        }

        DubuChanges(String val, int id, String name, String engMean, String korMean, String desc) {
            this.id = id;
            this.name = name;
            this.val = convertTenTobitValue(val);
            this.korMean = korMean;
            this.engMean = engMean;
            this.desc = desc;

        }

        public static DubuChanges findName(String name){
            for (int i =0 ; i < DubuChanges.values().length ; i++){
                if(DubuChanges.values()[i].name.equals(name)){
                    return DubuChanges.values()[i];
                }
            }
            return null;
        }

        int convertTenTobitValue(String str) {

            int tenValue =  Integer.valueOf(str);
            int val = 0;

            val = val + tenValue / 100000 * 32;
            tenValue = tenValue % 100000;

            val = val + tenValue / 10000 * 16;
            tenValue = tenValue % 10000;

            val = val + tenValue / 1000 * 8;
            tenValue = tenValue % 1000;

            val = val + tenValue / 100 * 4;
            tenValue = tenValue % 100;

            val = val + tenValue / 10 * 2;
            tenValue = tenValue % 10;

            val = val + tenValue;

            return val;
        }

        @Override
        public String toString() {
            String intStringVal = Integer.toBinaryString(this.val);
            return  this.val + " " +
                    //String.format("%04d", 1232) + " " +
                    //Integer.toBinaryString(this.val) + " " +
                    String.format("%03d", Integer.valueOf(intStringVal)) + " " +
                    this.desc;
        }
    }

    static int MinOfHour = 60;

    static TextView txtTime;
    static TextView txtDesc;
    //static Button btnTime;

    static ImageView secPoint;
    static ImageView hourPoint;
    static ImageView dayPoint;

    private Handler mHandler;
    private Runnable mRunnable;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /*
        // flash
        Camera mycam = Camera.open();
        Camera.Parameters p = mycam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        mycam.setParameters(p);
        //time passes
        p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        mycam.setParameters(p);
        mycam.release();
        */

        Intent intent = new Intent(getApplicationContext(), AlramActivity.class);
        AlarmManager manager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        PendingIntent sender = PendingIntent.getActivity(getApplicationContext(), 0, intent, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, (calendar.get(Calendar.HOUR_OF_DAY)+1) );
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() , AlarmManager.INTERVAL_HOUR, sender);

        /*
        int icon = android.R.drawable.ic_menu_day; // 아이콘을 지정
        CharSequence tickerText = "Hello C"; // 티커 메시지
        long when = System.currentTimeMillis(); // 노티피케이션의 시간을 지정
        Context context = getApplicationContext(); // 어플리케이션의 컨텍스트를 얻음
        CharSequence contentTitle = "My Change notification"; // 타이틀 메시지
        CharSequence contentText = "Hello Change!"; // 텍스트 메시지
        //Intent notificationIntent = new Intent(this, Chap8RedActivity.class);
        //PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        // 노티피케이션을 초기화 하고, 노티피케이션 사용을 위해 설정
        Notification notification = new Notification(icon, tickerText, when);
        notification.setLatestEventInfo(context, contentTitle, contentText,
                null);
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
        */

        txtTime = (TextView) findViewById(R.id.txt_time);
        txtDesc = (TextView) findViewById(R.id.txt_desc);
        //btnTime = (Button) findViewById(R.id.btn_time);
        dayPoint = (ImageView) findViewById(R.id.img_daypoint);
        hourPoint = (ImageView) findViewById(R.id.img_timepoint);
        secPoint = (ImageView) findViewById(R.id.img_sec);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                runTime();
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 500);

        Changes.P001010.desc = "수산건 , 힘든 상황인데 용쓰는 모양. 안쓰럽네. 달리려하나 다리가 말을 듣지 않는다. 절름발이 상태.";
        Changes.P100110.desc = "택뢰수 , 따를 수. 선순환. 벼락이 치다. 벼락이 구름되어 다욱 구름이 많아지고 있는 모양. 잘되어 가고 있다. 선순환, 수필";
        Changes.P101101.desc = "중화리 , 떠나리 이별 눈물";
        Changes.P010111.desc = "천수송 , 소송,다툼";
        Changes.P011101.desc = "화풍정 , 솥정. 이별,아픔이 해결하려고 하는데 해결이 되어 가는 과정. 솥에 약 넣고 다려 먹는 격";
        Changes.P111001.desc = "산천대축 , 크게저축. 바닥이 생성되는 현상. 큰 저축, 큰 브레이크. 대세 전환의 힘.";
        Changes.P001100.desc = "뇌산소과 , 지나치다. 높이 오르다 살짝 지친현상.";
        Changes.P011111.desc = "천풍구 , 만날구. 만날 수 있는 상태. 천지괘가 제일 정한 상태이다. 옆옆은 그나마 낫다. 움직일 수 있는 상태. 대세 하락의 징조가 될 수 있다.";
        Changes.P001111.desc = "천산돈 , 달아날돈. 도망가라. 이 정도 떨어 졌으면 쌍봉이 온 후 대 낙폭이 있으니 도망가다.";
        Changes.P000111.desc = "천지비 , 아닐비. 막힐부. 거부 , 부정적";
        Changes.P010010.desc = "중수감 , 구덩이감, 근심, 위험, 진퇴양란.";
        Changes.P100001.desc = "산뢰이 , 턱이.입을 벌리고 떠느니 흉하다. 바닥의 첫빵 상승.산속에 상승이라 크지 않고, 때가 않좋다. 산속에 벼락이 치니 우중충하니 음산.";
        Changes.P011010.desc = "수풍정 , 땅속에 기오나오는 용쓰는 모습인데 다시 찬기운이 들어간다. 우는 아이 뺨때리는 겪. 땅속에서 못 벗어나오도록 찬기운이 가늑하다. 우물!";
        Changes.P001011.desc = "풍산점 , 나아갈 점. 한 박자 쉬 또 나아 갈수 있다. 한번에 못 내려 간다. 대하락을 위한 약각의 상승 하락의 기운을 진행으로 바꾸다";
        Changes.P000011.desc = "풍지관 , 볼관. 평지를 살펴보다.";
        Changes.P011011.desc = "중풍손 , 손쾌손, 유순하다, 따르다, 부드러운 조화.";
        Changes.P010011.desc = "풍수환 , 흩어질환. 풀리다. 풀려서 기쁘다.";
        Changes.P100000.desc = "지뢰복 , 다시복. 0에서 다시 심장이 뛰다.";
        Changes.P110101.desc = "화택규 , 사팔눈규, 눈부릅뜰규. 흩어지는 인재들 속에 다시 잘해 보려고 모으는 모습. 계속 영유하기 위해 용쓰는 모양. 경쟁.";
        Changes.P010110.desc = "택수곤 , 곤란하다. 잘 모았는데 못 쓰는 상태.";
        Changes.P011110.desc = "택풍대과 , 크게 지나치다. 크게 쉬다.";
        Changes.P001110.desc = "택산함 , 느끼다. 쉬고 다시 일어 나지 좋구나. 좋은 휴식이였음. 음양의 조화.";
        Changes.P000110.desc = "택지췌 , 모으다. 모아서 뺄 사람 다 빼고, 정리하고 다 모았다.";
        Changes.P110110.desc = "중택태 , 기쁘구나. 모여가는 모양을 보니 기쁘구나.";
        Changes.P111110.desc = "택천쾌 , 터놓을쾌.결정하다. break-thoug 정리가 끝난상태 부정적인 상황의 정리가 끝. 모인 것을 결정하다.";
        Changes.P100100.desc = "중뢰진 , 벼락  상승 크게 승천하는 모양.";
        Changes.P110010.desc = "수택절 , 절제. 녹여내려고 애쓰는 모습. 성과가 크지 않으니 무리하지 말고 절재해라.";
        Changes.P111010.desc = "수천수 , 기다릴 수.  할량은 다 했으니 접고 오르길 기다리다.";
        Changes.P100101.desc = "화뢰서합 , 우지끈 입다문 모양. 이대로 안되겠다. 새로운 계획 제안서를 만드는 시기. 우지근";
        Changes.P110001.desc = "산택손 . 덜손. 손해. 용을써도 손해를 본다. 정지. 하지 마라.";
        Changes.P000100.desc = "뇌지예 . 미리예.  다시 가자 다떨어진 상태. 가자. 제후의 출정 모습.";
        Changes.P011100.desc = "뇌풍항 . 한결같다. 어 뭐지 조금 이상한데. 밀리는 형상. 오르지 못하고 평행을 걷는 형상";
        Changes.P010100.desc = "뇌수해 . 풀칠해. 일이 풀리다. 쭉쭉 뻣어나기전이라 그러함. 수가 뇌를 누르지 못하고 풀리다.";
        Changes.P111100.desc = "뇌천대장 . 크게장성하다. 맹화의 뿔";
        Changes.P110100.desc = "뇌택귀매 . 때를 기다려라. 작은 결실이라 다시 시기를 기다려다. 확 큰 일은 다음에 있다.";
        Changes.P001001.desc = "중산간 . 그칠간 .멈추어라";
        Changes.P010101.desc = "화수미제 .  곧 이루어진다. 동튼 자리로 다시 돌아옴.";
        Changes.P111000.desc = "지천태 , 클태. 작은 것이 가고 큰게 온다. 정한 상태인데 한 입 먹다. 한입이나 크다.";
        Changes.P001000.desc = "지산겸 , 겸손. 승이 작으니 겸손하다.";
        Changes.P100011.desc = "풍뢰익 , 더해줌. 낙하에 승이 상호보완하여 이롭다.";
        Changes.P110011.desc = "풍택중부 , 절제하여 믿게 한다.  흐러가다 살짝 멈추다.   흘러간다. 따라 가는 구나. 절제하는 모습.";
        Changes.P111011.desc = "풍천소축 , 조금쌓는다.  모은것 중에 제일 작다. 구름이 빡빡하나 비가 오지 않는다.";
        Changes.P000000.desc = "중지곤 , 바닥 평온하다. 땅, 바닥, 부드러움";
        Changes.P101100.desc = "뇌화풍 , 돌아가 모인다.";
        Changes.P000001.desc = "산지박 , 깍일박. 바닥꼭지. 무너지다.";
        Changes.P000010.desc = "수지비 , 견주다. 편안. 땅에 비가 온다.";

        Changes.P000101.desc = "화지진 , 나라이름진. 나아간다. 용썼는데 안되서 편안하다. 해가 땅위에 있어 나아간다.";
        Changes.P001101.desc = "화산려 , 나그네. 모아서 하려는데 잘 안되서 다시 안되는데 할려고하면 나가야지.";
        Changes.P010000.desc = "지수사 , 무리,집단,모임 모인다. 모아야 녹는가 보다.";
        Changes.P010001.desc = "산수몽 , 완전 바닥 다음. 안좋은데..  바닥이나 근심이 많다? 꿈을 꾸다. 될까 싶기도 하고";
        Changes.P011000.desc = "지풍승 . 오름. 첫 파동 다음에 쉼. 뚜둥. 쉬었는데도, 오름의 기쁨이 더 커서 여기까지 오다";
        Changes.P011001.desc = "산풍고 . 마지막 고통? 벌레가 썩는 형국";
        Changes.P100010.desc = "수뢰둔 .  진칠둔, 어려운둔.   안좋은데. 용쓰는 형국 늪에서 오르며 애쓴는 형상 ";
        Changes.P100111.desc = "천뢰무망 . 마지막 상승. 허나 약하다. 이제 상승의 힘이 없다.";
        Changes.P101000.desc = "지화명이 , 불쏙의 흙. 출정식 북일리고 그 다음 모양. 살짝 꺽임. 암흙, 폭정. 불이이 빛을 못보는 상황";
        Changes.P101001.desc = "산화비 , 꾸미다.크다. 화려하게 불을 째려하나 장작이 약하다. 허세";
        Changes.P101010.desc = "수화기제 , 이미 이루어졌다. 부족함이 없도다. 내려가고 올라가려하니 상호 보안 좋다";
        Changes.P101011.desc = "풍화가인 , 집사람. 각자 임무에 충실하다. 각자 직분 단도리를 단단히 하라. 흔들리지 않게 장비철저, 단도리 확인!";
        Changes.P101110.desc = "택화혁 , 혁신. 1차 로켓 엔진을 다 써서, 2차 엔진이 필요하다.";
        Changes.P101111.desc = "천화동인 , 사람과 함께하다. 다 벌었다. 사람을 얻을 수 있는 시기. 새로운 동업을 하기 좋다.";
        Changes.P110000.desc = "지택임 . 임하다. 첫움직임이 유효하다. 강이되어 자라나매 기뻐하다.";
        Changes.P110111.desc = "천택리 . 밟다. 마지막 상승의 효과";
        Changes.P111111.desc = "중천건 . 양극 ";
        Changes.P111101.desc = "화천대유 . 크게송유하다. 잘써먹었다. ";
    }

    private void runTime() {
        GregorianCalendar today = new GregorianCalendar();
        Calendar cal = Calendar.getInstance(Locale.KOREA);

        int dayOfYear = today.get(today.DAY_OF_YEAR);
        int minOfDay = cal.get(Calendar.HOUR_OF_DAY) * MinOfHour + cal.get(Calendar.MINUTE);
        int secOfMin = cal.get(Calendar.SECOND);
        int dayHour =  cal.get(Calendar.HOUR_OF_DAY);

        int dayDegree = 360 * (dayOfYear+8) / 365 ;
        int hourDegree = 360 * minOfDay / 1440;
        int secDegree = 360 * secOfMin / 60 ;

        dayPoint.setRotation(-dayDegree);
        hourPoint.setRotation(-hourDegree);
        secPoint.setRotation(-secDegree);

        String timeStr =cal.get ( Calendar.YEAR ) + "년 " + ( cal.get ( Calendar.MONTH ) + 1 ) + "월 " + cal.get ( Calendar.DATE ) + "일 "
                + cal.get ( Calendar.HOUR_OF_DAY ) + "시 " +cal.get ( Calendar.MINUTE ) + "분 " + cal.get ( Calendar.SECOND ) + "초 ";

        String cName =  ChangeStr(secDegree)+ ChangeStr(hourDegree) ;
        timeStr = timeStr + Changes.findName(cName).desc.substring(0,4);
        txtTime.setText(timeStr);
        txtDesc.setText(Changes.findName(cName).desc);
        mHandler.postDelayed(mRunnable, 500);

        if(dayHour%2 == 1 &&cal.get(Calendar.MINUTE) == 0  ){
            //if(cal.get(Calendar.MINUTE) == 0  ){
            if(dayHour == 23 ||dayHour == 5 ||dayHour == 11 ||dayHour == 17) vibrationFire(0);
            if(dayHour == 1 ||dayHour == 7  ||dayHour == 13 ||dayHour == 19) vibrationFire(1);
            if(dayHour == 3 ||dayHour == 9  ||dayHour == 15 ||dayHour == 21) vibrationFire(2);
        }

        if(dayHour%2 == 1 && cal.get(Calendar.MINUTE) == 0  ){
            //if(cal.get(Calendar.MINUTE) == 0  ){
            unlockScreen();
        }

    }

    @Override
    protected void onDestroy() {
        Log.i("test", "onDstory()");
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }

    private  String ChangeStr(int degree){

        if(degree >=0 && 45 >degree){
          return "000";
        }else if(degree <90){
            return "010";
        }else if(degree <135){
            return "100";
        }else if(degree <180){
            return "110";
        }else if(degree <225){
            return "111";
        }else if(degree <270){
            return "101";
        }else if(degree <315){
            return "011";
        }else{
            return "001";
        }
    }

    private void vibrationFire(int i) {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        List<long[]> pats = new ArrayList<long[]>();
        pats.add(new long[]{1000L, 1000L,1000L, 1000L,1000L,1000L});
        pats.add(new long[]{500L, 500L,500L, 500L,500L, 500L});
        pats.add(new long[]{250L, 250L,250L, 250L,250L,250L, 250L});
        vibe.vibrate(pats.get(i), -1);
    }

    private void unlockScreen() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        wl.acquire();
        wl.release();
    }

}
