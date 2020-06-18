package baseball.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Player {
	private int id;
	private String name;
	private int num;
	private String team;		
	private String position;
}
