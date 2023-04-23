package com.tule.coin.util;

public class SimpleHash {
    public static final int TOTAL_HASH_COUNT = 4;
    public interface HashInterface {
        int calcHash(String data);
    }

    public static int calcHash(String data, int selector){
        HashInterface hashInterface = GetHashBySelector(selector);
        return hashInterface.calcHash(data);
    }

    private static class DEKHashEx implements HashInterface{

        @Override
        public int calcHash(String data) {
            int   i = 0;
            int   ulHash = data.length();

            for (i = 0; i < data.length(); ++i)
            {
                ulHash = ((ulHash << 5) ^ (ulHash >> 27)) ^ (data.charAt(i));
            }

            return (ulHash & 0x7FFFFFFF);
        }
    }

    private static class APHash implements HashInterface{
        @Override
        public int calcHash(String data) {
            int i = 0;
            int ulHash = 0;

            for (i = 0; i < data.length(); i++)
            {
                ulHash ^= ((i & 1) == 0) ? ( (ulHash << 7) ^ data.charAt(i) ^ (ulHash >> 3)) :
                        (~((ulHash << 11) ^ data.charAt(i) ^ (ulHash >> 5)));
            }

            return ulHash & (0x7FFFFFFF);
        }
    }

    private static class DJBHash implements HashInterface{

        @Override
        public int calcHash(String data) {
            int  i = 0;
            int  ulHash = 5381;

            for(i = 0; i < data.length(); i++)
            {
                ulHash = ((ulHash << 5) + ulHash) + data.charAt(i);
            }

            return (ulHash & 0x7FFFFFFF);
        }
    }

    private static class OnebyOneHash implements HashInterface{

        @Override
        public int calcHash(String data) {
            int i = 0;
            int ulHash = 0;
            for (i = 0; i < data.length(); ++i)
            {
                ulHash += data.charAt(i);
                ulHash += (ulHash << 10);
                ulHash ^= (ulHash >> 6);
            }
            ulHash += (ulHash << 3);
            ulHash ^= (ulHash >> 11);
            ulHash += (ulHash << 15);
            return (ulHash & 0x7FFFFFFF);
        }
    }

    private static HashInterface GetHashBySelector(Integer selector)
    {
        switch (selector % TOTAL_HASH_COUNT)
        {
            case 0:
                return new DEKHashEx();
            case 1:
                return new APHash();
            case 2:
                return new DJBHash();
            case 3:
                return new OnebyOneHash();
        }
        return new APHash();
    }

}
