import dayjs from 'dayjs/esm';

export interface IRhAgent {
  id: string;
  matric?: number | null;
  decjour?: string | null;
  dateffrh?: dayjs.Dayjs | null;
  decoper?: string | null;
  deccent?: number | null;
  decagenc?: number | null;
}

export type NewRhAgent = Omit<IRhAgent, 'id'> & { id: null };
