export interface IServiceRot {
  id: string;
  deccent?: number | null;
  decagenc?: number | null;
  decserv?: number | null;
  codgrp?: number | null;
  delserv?: string | null;
  ordserv?: number | null;
}

export type NewServiceRot = Omit<IServiceRot, 'id'> & { id: null };
