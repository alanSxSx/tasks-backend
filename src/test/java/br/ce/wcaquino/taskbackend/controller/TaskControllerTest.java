package br.ce.wcaquino.taskbackend.controller;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;


public class TaskControllerTest {
	
	 	@Mock
	    private TaskRepo taskRepo; // ✅ mockado corretamente

	    @InjectMocks
	    private TaskController controller; // ✅ recebe o mock

	    @Before
	    public void setup() {
	        MockitoAnnotations.initMocks(this); // ou use openMocks se estiver com versão recente
	    }
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {		
		Task todo = new Task();
		todo.setDueDate(LocalDate.now());		
		try {
			controller.save(todo);
			Assert.fail("Não deveria chegar nesse ponto");
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the task description", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {		
		Task todo = new Task();
		todo.setTask("Descricao");
		
		try {
			controller.save(todo);
			Assert.fail("Não deveria chegar nesse ponto");
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the due date", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {		
		Task todo = new Task();
		todo.setDueDate(LocalDate.now().minusDays(1));
		todo.setTask("Descricao");		
		try {
			controller.save(todo);
			Assert.fail("Não deveria chegar nesse ponto");
		} catch (ValidationException e) {
			Assert.assertEquals("Due date must not be in past", e.getMessage());
		}
	}
	
	@Test
	public void deveSalvarTarefacomSucesso() throws ValidationException {	
		Task todo = new Task();
		todo.setDueDate(LocalDate.now());
		todo.setTask("Descricao");	
		controller.save(todo);	
		Mockito.verify(taskRepo).save(todo);
		
	}

}
