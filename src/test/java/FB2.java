//Welcome to Facebook!
//
//        This is just a simple shared plaintext pad, with no execution capabilities.
//
//        When you know what language you would like to use for your interview,
//        simply choose it from the dropdown in the top bar.
//
//        Enjoy your interview!
//        hi
//
//
//        Given the time (two integers - hour and minute), tell the angle between the hour and minute hand of an analog watch.
//        hour hand = 6
//        minute hand = 12
//        angle is 180 degrees
//
//
//
//        int getAngle(int hour, int minute) {
//        mHour = convertMinutesToHour(minute);
//
//        diff = hMinute - minutes;
//
//
//        degrees = diff * 6;
//
//        return degrees;
//        }
//
//        int convertHourToMinutes(int hour) {
//        return hour * 60;
//        }
//
//
//
//        hour hand = 6
//        minute hand = 12 / 60 = 0.2 hour
//
//        6 + 0.2 = 6.2
//
//        348*6
//
//        angle is 180 degrees
//
//        360 / 60
//        360 / 12 = 30
//
//        1 hour = 30 degrees
//        180.6
//
//
//        . Add one to integer represented as array of digits (characters)
//        Example : array = ['1', '2', '3'] result = ['-', '1', '2', '4']
//
//        O(N)
//        O(n)
//
//        -123 - 124
//        129 - 130
//        999 - 1000
//        892  -  893
//        899  -
//
//
//        -123   -->  -122
//
//        -10   - -9
//        - 230  -  229
//        - 200  199
//        - 0000
//
//
//
//        char[] addOneWithSign(char[] arr) {
//
//        if(arr[0] == '+')    addOne(ReduceArray(arr,1,arr.length));
//
//        else  {
//        int n = arr.length - 1;
//
//        while( n >= 1 ) {
//        int x = arr[n] - '0';
//        if( x > 0 ) {
//        arr[n] = x - 1;
//        return arr;
//        }
//
//        arr[n] = '9';
//        n--;
//        }
//        }
//        }
//
//
//        char[] addOne(char[] arr) {
//
//        int n = arr.length - 1;
//
//        while(n >= 0) {
//        if(x < 9) {
//        int x = arr[n] - '0';
//        arr[n] = x + 1;
//        return arr;
//        }
//
//
//        arr[n] = '0';
//        n--;
//        }
//
//        int[] result = new int[arr.length + 1];
//        result[0] = '1';
//        return result;
//        }
//
//
//
//
//
//
