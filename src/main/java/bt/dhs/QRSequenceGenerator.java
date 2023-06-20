package bt.dhs;

import java.util.Locale;

public class QRSequenceGenerator implements Generator{
    public class SequenceGenerator implements Iterator{
//        int limit = 0xfffff;
//        int limit = 0x0EA60; // for units. Equivalent is 60,000
//        int limit = 0x203A1; // 132,001 units till
//        int index= 0x14052; //82,002 units
        int limit = 0x953B; // 38203 buildings
        int index = 0x6E2B; //28202

        @Override
        public boolean hasNext() {
            if(index > limit){
                return false;
            }
            return true;
        }

        @Override
        public String next(String lead) {
            if(this.hasNext()){
                String hexCode = String.format("%1$05X",index);
                String code = lead.toUpperCase(Locale.ROOT) + hexCode;
                index ++;
                return code;
            }
            return null;
        }
    }

    @Override
    public Iterator getIterator() {
        return new SequenceGenerator();
    }
}
