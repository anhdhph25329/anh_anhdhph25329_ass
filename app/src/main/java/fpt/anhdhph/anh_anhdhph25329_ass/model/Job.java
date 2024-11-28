package fpt.anhdhph.anh_anhdhph25329_ass.model;

public class Job {
    int id;
    String name;
    String content;
    int status;
    String startDay;
    String endDay;

    public String toString(){
        return "ID: " + id + ", Name: " + name + ", Content: " + content +
                ", Status: " + status + ", Start Day: " + startDay + ", End Day: " + endDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
}
