import { TestBed } from '@angular/core/testing';

import { KeycloakInitService } from './keycloak-init-service.service';

describe('KeycloakInitServiceService', () => {
  let service: KeycloakInitService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KeycloakInitService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
