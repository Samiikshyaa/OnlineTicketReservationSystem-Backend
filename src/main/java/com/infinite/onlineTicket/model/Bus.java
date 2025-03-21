package com.infinite.onlineTicket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "buses")
public class Bus implements Serializable {
    @Id
    @SequenceGenerator(name = "buses_seq_generator", sequenceName = "buses_sequence_generator",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buses_sequence_generator")
    private Long id;
    @Column(name = "bus_number")
    private String busNumber;
    private Integer capacity;
    @ManyToMany(targetEntity = Route.class, fetch = FetchType.LAZY)
    @JoinTable(name ="bus_route",
            joinColumns= @JoinColumn(name= "bus_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_buses_bus_id")),
            inverseJoinColumns = @JoinColumn(name= "route_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_buses_route_id")))
    private List<Route> routes;
    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Seat> seats;
}
