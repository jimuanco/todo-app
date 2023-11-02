package myproject.todoapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Todo")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userId;
    private String title;
    private boolean done;

    public void assignLoggedInUserID(String loggedUserId) {
        this.userId = loggedUserId;
    }

}
