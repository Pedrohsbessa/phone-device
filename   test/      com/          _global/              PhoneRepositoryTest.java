import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PhoneRepositoryTest {

    @Test
    void testAddPhone() {
        PhoneRepository repository = new PhoneRepository();
        Phone phone = new Phone("1234567890");
        repository.addPhone(phone);
        assertEquals(phone, repository.getPhone("1234567890"));
    }

    @Test
    void testGetPhoneNotFound() {
        PhoneRepository repository = new PhoneRepository();
        assertNull(repository.getPhone("nonexistent"));
    }

    @Test
    void testRemovePhone() {
        PhoneRepository repository = new PhoneRepository();
        Phone phone = new Phone("1234567890");
        repository.addPhone(phone);
        repository.removePhone("1234567890");
        assertNull(repository.getPhone("1234567890"));
    }

    @Test
    void testRemovePhoneNotFound() {
        PhoneRepository repository = new PhoneRepository();
        assertFalse(repository.removePhone("nonexistent"));
    }

    @Test
    void testUpdatePhone() {
        PhoneRepository repository = new PhoneRepository();
        Phone phone = new Phone("1234567890");
        repository.addPhone(phone);
        Phone updatedPhone = new Phone("1234567890", "newOwner");
        repository.updatePhone(updatedPhone);
        assertEquals(updatedPhone, repository.getPhone("1234567890"));
    }

    @Test
    void testUpdatePhoneNotFound() {
        PhoneRepository repository = new PhoneRepository();
        Phone updatedPhone = new Phone("nonexistent", "newOwner");
        assertFalse(repository.updatePhone(updatedPhone));
    }
}