package qa.candidate.model;

import java.util.List;

public class GenerateTokenRequest {
        private List<Claim> clains;
        public List<Claim> getClains() {
            return clains;
        }
        public void setClains(List<Claim> clains) {
            this.clains = clains;
        }
        public static class Claim {
            private String key;
            private String value;
            public String getKey() {
                return key;
            }
            public void setKey(String key) {
                this.key = key;
            }
            public String getValue() {
                return value;
            }
            public void setValue(String value) {
                this.value = value;
            }
        }
    }