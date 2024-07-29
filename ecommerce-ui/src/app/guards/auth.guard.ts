import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { SessionStorageService } from '../services/session-storage.service';

export const authGuard: CanActivateFn = (route, state) => {
  const sesion= inject(SessionStorageService);
  const router= inject(Router);

  if(sesion.getItem('userData') != null){
    return true;
  }

  return router.createUrlTree(['/user/login']);
};
