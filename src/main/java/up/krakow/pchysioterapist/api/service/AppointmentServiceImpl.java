package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.*;
import up.krakow.pchysioterapist.api.exception.AppointmentAlreadyBookedException;
import up.krakow.pchysioterapist.api.exception.DatesException;
import up.krakow.pchysioterapist.api.exception.TimeSlotNotAvailableException;
import up.krakow.pchysioterapist.api.mapper.AppointmentMapper;
import up.krakow.pchysioterapist.api.mapper.MassageMapper;
import up.krakow.pchysioterapist.api.mapper.UsersMapper;
import up.krakow.pchysioterapist.api.model.Appointment;
import up.krakow.pchysioterapist.api.model.Users;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentStatus;
import up.krakow.pchysioterapist.api.model.enums.ERole;
import up.krakow.pchysioterapist.api.repository.AppointmentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final MassageService massageService;

    private final UserService userService;
    private final AppointmentMapper appointmentMapper;
    private final UsersMapper usersMapper;
    private final MassageMapper massageMapper;

    @Override
    public Appointment createAppointment(AppointmentDTO dto) {
        if(!checkIfDateIsFree(dto.getStartDate(), dto.getEndDate()))
            throw new TimeSlotNotAvailableException("Proszę wybrać inną datę, ta jest już zajęta.");
        Appointment appointment = new Appointment();
        appointment.setStartDate(dto.getStartDate());
        appointment.setEndDate(dto.getEndDate());
        appointment.setMassage(massageService.getMassageById(dto.getMassageId()));
        appointment.setStatus(dto.getStatus());
        appointment.setUsers(userService.getUserById(dto.getUserId()));
        appointment.setUrlKey(UUID.randomUUID().toString());
        appointmentRepository.save(appointment);
        return appointment;
    }

    public boolean checkIfDateIsFree(LocalDateTime startDate, LocalDateTime endDate) {
        List<Appointment> appointments = appointmentRepository.findByStartDateBetweenOrEndDateBetween(startDate, endDate, startDate, endDate);
        return appointments.isEmpty();
    }

    @Override
    public Appointment getAppointment(Integer appointmentId) {
        return appointmentRepository.findByAppointmentId(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + "does not exist!"));
    }

    @Override
    public List<AppointmentResponseDTO> getAllFreeAppointments() {
        List<Appointment> appointments = appointmentRepository.findAllByStatusAndStartDateBetween(String.valueOf(EAppointmentStatus.FREE), LocalDateTime.now(), LocalDateTime.now().plusDays(5));
        Map<LocalDate, List<SimpleAppointmentDTO>> appointmentMap = groupSimpleAppointmentsByDate(appointmentMapper.mapToSimpleAppointmentDTOList(appointments));
        return createSimpleAppointmentDTOList(appointmentMap);
    }

    @Override
    public List<Appointment> getAllFinishedAppointments() {
        return appointmentRepository.findAllByStatus(String.valueOf(EAppointmentStatus.FINISHED));
    }

    @Override
    public Appointment editAppointment(AppointmentDTO dto, Integer appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + "does not exist!"));
        appointment.setStartDate(dto.getStartDate());
        appointment.setEndDate(dto.getEndDate());
        appointment.setMassage(massageService.getMassageById(dto.getMassageId()));
        appointment.setUsers(userService.getUserById(dto.getUserId()));
        appointment.setStatus(dto.getStatus());
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment setStatusToFinished(Integer appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + "does not exist!"));
        appointment.setStatus(EAppointmentStatus.FINISHED.toString());
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment bookAppointment(Integer appointmentId, AppointmentDTO dto) {
        Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + " does not exist!"));
        if (appointment.getStatus().equals(EAppointmentStatus.BOOKED.toString()))
            throw new AppointmentAlreadyBookedException("Termin jest już zarezerwowany!");
        appointment.setStatus(String.valueOf(EAppointmentStatus.BOOKED));
        appointment.setMassage(massageService.getMassageById(dto.getMassageId()));
        appointment.setUsers(userService.getUserById(dto.getUserId()));
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment bookAppointmentAsGuest(Integer appointmentId, AppointmentWithEmailDTO dto) {
        Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + " does not exist!"));
        if (appointment.getStatus().equals(EAppointmentStatus.BOOKED.toString()))
            throw new AppointmentAlreadyBookedException("Termin jest już zarezerwowany!");
        appointment.setStatus(String.valueOf(EAppointmentStatus.BOOKED));
        appointment.setMassage(massageService.getMassageById(dto.getMassageId()));
        Users user;
        if (userService.getGuestByEmail(dto.getUserEmail()) != null)
            user = userService.getGuestByEmail(dto.getUserEmail());
        else user = new Users();
        user.setRole(ERole.GUEST);
        user.setEmail(dto.getUserEmail());
        user.setPhone(dto.getUserPhone());
        userService.saveUser(user);
        appointment.setUsers(user);
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public void deleteAppointment(Integer appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public Appointment cancelAppointment(Integer appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + "does not exist!"));
        appointment.setStatus(String.valueOf(EAppointmentStatus.FREE));
        appointment.setUsers(null);
        appointment.setMassage(null);
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public List<Appointment> findByStartDateBetweenOrderByStartDateAsc(LocalDateTime startDate) {
        return appointmentRepository.findByStartDateBetweenOrderByStartDateAsc(startDate, startDate.plusDays(7));
    }

    @Override
    public List<CalendarDTO> getWeeklyCalendar(LocalDateTime startDate) {
        List<Appointment> appointmentList = findByStartDateBetweenOrderByStartDateAsc(startDate);
        List<AppointmentDTO> appointmentDTOList = createAppointmentDTOList(appointmentList);
        Map<LocalDate, List<AppointmentDTO>> appointmentMap = groupAppointmentsByDate(appointmentDTOList);
        return createCalendarDTOList(appointmentMap);
    }

    private List<AppointmentDTO> createAppointmentDTOList(List<Appointment> appointmentList) {
        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            AppointmentDTO appointmentDTO = appointmentMapper.mapToAppointmentDTO(appointment);
            enrichAppointmentDTO(appointmentDTO);
            appointmentDTOList.add(appointmentDTO);
        }
        return appointmentDTOList;
    }

    private void enrichAppointmentDTO(AppointmentDTO appointmentDTO) {
        if (appointmentDTO.getUserId() != null)
            appointmentDTO.setUsersDTO(usersMapper.mapUsersToUsersDTO(userService.getUserById(appointmentDTO.getUserId())));
        if (appointmentDTO.getMassageId() != null)
            appointmentDTO.setMassageDTO(massageMapper.massageToMassageDTO(massageService.getMassageById(appointmentDTO.getMassageId())));
    }

    private Map<LocalDate, List<AppointmentDTO>> groupAppointmentsByDate(List<AppointmentDTO> appointmentDTOList) {
        Map<LocalDate, List<AppointmentDTO>> appointmentMap = new TreeMap<>();
        for (AppointmentDTO appointmentDTO : appointmentDTOList) {
            LocalDate date = appointmentDTO.getStartDate().toLocalDate();
            List<AppointmentDTO> dateAppointments = appointmentMap.computeIfAbsent(date, k -> new ArrayList<>());
            dateAppointments.add(appointmentDTO);
        }

        for (List<AppointmentDTO> dailyAppointments : appointmentMap.values()) {
            dailyAppointments.sort(Comparator.comparing(AppointmentDTO::getStartDate));
        }

        return appointmentMap;
    }

    private Map<LocalDate, List<SimpleAppointmentDTO>> groupSimpleAppointmentsByDate(List<SimpleAppointmentDTO> appointmentDTOList) {
        Map<LocalDate, List<SimpleAppointmentDTO>> appointmentMap = new TreeMap<>();
        for (SimpleAppointmentDTO appointmentDTO : appointmentDTOList) {
            LocalDate date = appointmentDTO.getStartDate().toLocalDate();
            List<SimpleAppointmentDTO> dateAppointments = appointmentMap.computeIfAbsent(date, k -> new ArrayList<>());
            dateAppointments.add(appointmentDTO);
        }

        for (List<SimpleAppointmentDTO> dailyAppointments : appointmentMap.values()) {
            dailyAppointments.sort(Comparator.comparing(SimpleAppointmentDTO::getStartDate));
        }

        return appointmentMap;
    }


    private List<CalendarDTO> createCalendarDTOList(Map<LocalDate, List<AppointmentDTO>> appointmentMap) {
        List<CalendarDTO> calendarDTOList = new ArrayList<>();
        for (Map.Entry<LocalDate, List<AppointmentDTO>> entry : appointmentMap.entrySet()) {
            CalendarDTO calendarDTO = new CalendarDTO();
            calendarDTO.setLocalDate(entry.getKey().atStartOfDay());
            calendarDTO.setAppointmentDTOList(entry.getValue());
            calendarDTOList.add(calendarDTO);
        }
        return calendarDTOList;
    }

    private List<AppointmentResponseDTO> createSimpleAppointmentDTOList(Map<LocalDate, List<SimpleAppointmentDTO>> appointmentMap) {
        List<AppointmentResponseDTO> appointmentDTOS = new ArrayList<>();
        for (Map.Entry<LocalDate, List<SimpleAppointmentDTO>> entry : appointmentMap.entrySet()) {
            AppointmentResponseDTO dto = new AppointmentResponseDTO();
            dto.setLocalDate(entry.getKey().atStartOfDay());
            dto.setSimpleAppointmentDTO(entry.getValue());
            appointmentDTOS.add(dto);
        }
        return appointmentDTOS;
    }

    @Override
    public Appointment creteAppointmentForDate(LocalDateTime startDate, LocalDateTime endDate) {
        if(!checkIfDateIsFree(startDate, endDate))
            throw new TimeSlotNotAvailableException("Proszę wybrać inną datę, ta jest już zajęta.");
        Appointment appointment = new Appointment();
        appointment.setStartDate(startDate);
        appointment.setEndDate(endDate);
        appointment.setStatus(String.valueOf(EAppointmentStatus.FREE));
        return appointmentRepository.save(appointment);
    }


    @Override
    public List<Appointment> createAppointmentsForDay(List<StartEndDateDTO> dto) {
        List<Appointment> appointments = new ArrayList<>();
        for (StartEndDateDTO date: dto){
            LocalDateTime start = LocalDateTime.parse(date.getStartDate());
            LocalDateTime end = LocalDateTime.parse(date.getEndDate());
            if(!checkIfDateIsFree(start, end))
                throw new TimeSlotNotAvailableException("Proszę wybrać inną datę, ta jest już zajęta.");
            Appointment appointment = new Appointment();
            appointment.setStartDate(start);
            appointment.setEndDate(end);
            appointment.setStatus(String.valueOf(EAppointmentStatus.FREE));
            appointments.add(appointment);
            appointmentRepository.save(appointment);
        }
    return appointments;
    }

}
